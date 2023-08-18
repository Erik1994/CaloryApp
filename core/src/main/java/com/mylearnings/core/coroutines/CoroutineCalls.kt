package com.mylearnings.core.coroutines

import com.mylearnings.core.data.manager.ConnectionManager
import com.mylearnings.core.data.mapper.Mapper
import com.mylearnings.core.util.Resource
import com.mylearnings.core.util.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.Response

inline fun <RESPONSE, RESULT> safeApiCallFlow(
    mapper: Mapper<RESPONSE, RESULT>,
    dispatcherIo: CoroutineDispatcher,
    connectionManager: ConnectionManager,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline onFetchFailed: (Throwable) -> Unit = { }
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Resource.Success(mapper.map(it)))
            } ?: emit(Resource.Error(UiText.DynamicString(response.message().orEmpty()), null, response.code()))
        } else {
            emit(Resource.Error(UiText.DynamicString(response.message()), null, response.code()))
            onFetchFailed.invoke(Exception(response.message()))
        }
    } catch (t: Throwable) {
        emit(getErrorResource(connectionManager, t))
        onFetchFailed.invoke(t)
    }
}.flowOn(dispatcherIo)

suspend inline fun <RESPONSE, RESULT> safeApiCall(
    mapper: Mapper<RESPONSE, RESULT>,
    dispatcherIO: CoroutineDispatcher,
    connectionManager: ConnectionManager,
    crossinline apiCall: suspend () -> RESPONSE,
    crossinline onFetchFailed: (Throwable) -> Unit = { }
): Resource<RESULT> = withContext(dispatcherIO) {
    try {
        val response = apiCall.invoke()
        Resource.Success(mapper.map(response))
    } catch (t: Throwable) {
        onFetchFailed(Exception(t.message))
        getErrorResource(connectionManager, t)
    }
}

suspend inline fun <RESPONSE, RESULT> safeApiCallWIthResponse(
    mapper: Mapper<RESPONSE, RESULT>,
    dispatcherIO: CoroutineDispatcher,
    connectionManager: ConnectionManager,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline onFetchFailed: (Throwable) -> Unit = { }
): Resource<RESULT> = withContext(dispatcherIO) {
    try {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            response.body()?.let {
                Resource.Success(mapper.map(it))
            } ?: Resource.Error(UiText.DynamicString(response.message()), null, response.code())
        } else {
            onFetchFailed(Exception(response.message()))
            Resource.Error(UiText.DynamicString(response.message()), null, response.code())
        }
    } catch (t: Throwable) {
        onFetchFailed(Exception(t.message))
        getErrorResource(connectionManager, t)
    }
}

inline fun <RESPONSE, RESULT> safeCashedApiCall(
    mapper: Mapper<RESPONSE, RESULT>,
    connectionManager: ConnectionManager,
    dispatcherIo: CoroutineDispatcher,
    crossinline dbQuery: () -> Flow<RESULT>,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline dbSaver: suspend (RESULT) -> Unit,
    crossinline shouldFetchFromApi: (RESULT) -> Boolean = { true },
    crossinline onFetchFailed: (Throwable) -> Unit = {}
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val preCachedData = dbQuery.invoke().catch { }.first()
    val cachedFlow = if (shouldFetchFromApi.invoke(preCachedData)) {
        emit(Resource.Loading(preCachedData))
        try {
            val responseResult = apiCall.invoke()
            if (responseResult.isSuccessful) {
                responseResult.body()?.let {
                    dbSaver.invoke(mapper.map(it))
                }
            }
            dbQuery.invoke().map { Resource.Success(it) }
        } catch (t: Throwable) {
            val errorMessage = connectionManager.getErrorMessage(t)
            onFetchFailed.invoke(t)
            dbQuery.invoke().map {
                Resource.Error(errorMessage, it)
            }.catch {
                onFetchFailed.invoke(t)
                Resource.Error(errorMessage, preCachedData)
            }
        }
    } else {
        dbQuery.invoke().map { Resource.Success(it) }
    }
    emitAll(cachedFlow)
}.flowOn(dispatcherIo)

fun getErrorResource(connectionManager: ConnectionManager, t: Throwable): Resource<Nothing> {
    val errorMassage = connectionManager.getErrorMessage(t)
    return if (connectionManager.checkNetworkConnection()) {
        Resource.NoInternetConnection(message = errorMassage)
    } else {
        Resource.Error(errorMassage, null)
    }
}