package com.mylearnings.core.util


sealed class Resource<out T> {
    data class Success<out T>(
        val data: T,
        val code: Int? = null
    ) : Resource<T>()

    data class Error<out T>(
        val message: UiText? = null,
        val data: T?,
        val code: Int? = null
    ) : Resource<T>()

    data class Loading<out T>(
        val data: T?
    ) : Resource<T>()

    data class NoInternetConnection(val message: UiText): Resource<Nothing>()
}