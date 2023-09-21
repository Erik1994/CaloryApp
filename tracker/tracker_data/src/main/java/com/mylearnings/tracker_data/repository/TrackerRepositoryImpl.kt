package com.mylearnings.tracker_data.repository

import com.mylearnings.core.coroutines.safeApiCall
import com.mylearnings.core.data.manager.ConnectionManager
import com.mylearnings.core.util.Resource
import com.mylearnings.core.util.orDefault
import com.mylearnings.tracker_data.local.dao.TrackerDao
import com.mylearnings.tracker_data.mapper.PRODUCTS_TO_TRACKABLE_FOODS
import com.mylearnings.tracker_data.mapper.PRODUCT_TO_TRACKABLE_FOOD
import com.mylearnings.tracker_data.mapper.TRACKED_FOOD_ENTITY_TO_TRACKED_FOOD
import com.mylearnings.tracker_data.mapper.TRACKED_FOOD_TO_TRACKED_FOOD_ENTITY
import com.mylearnings.tracker_data.remote.api.OpenFoodApi
import com.mylearnings.tracker_domain.model.TrackableFood
import com.mylearnings.tracker_domain.model.TrackedFood
import com.mylearnings.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi,
    private val dispatcherIO: CoroutineDispatcher,
    private val connectionManager: ConnectionManager
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<TrackableFood>> {
        return safeApiCall(
            mapper = PRODUCTS_TO_TRACKABLE_FOODS,
            dispatcherIO = dispatcherIO,
            connectionManager = connectionManager,
            apiCall = {
                val searchDto = api.searchFood(
                    query = query,
                    page = page,
                    pageSize = pageSize
                )
                searchDto.products.filter {
                    val calculatedCalories = it.nutriments.carbohydrates100g.orDefault(0.toDouble()) * 4f +
                            it.nutriments.proteins100g.orDefault(0.toDouble()) * 4f +
                            it.nutriments.fat100g.orDefault(0.toDouble()) * 9f

                    val lowerBound = calculatedCalories * 0.99f
                    val upperBound = calculatedCalories * 1.01f
                    it.nutriments.energyKcal100g.orDefault(0.toDouble()) in (lowerBound .. upperBound)
                }
            }
        )
    }

    override suspend fun insertTrackedFood(food: TrackedFood) = withContext(dispatcherIO) {
        dao.insertTrackedFood(TRACKED_FOOD_TO_TRACKED_FOOD_ENTITY.map(food))
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) = withContext(dispatcherIO) {
        dao.deleteTrackedFood(TRACKED_FOOD_TO_TRACKED_FOOD_ENTITY.map(food))
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map {
            TRACKED_FOOD_ENTITY_TO_TRACKED_FOOD.mapList(it)
        }
    }
}