package com.mylearnings.tracker_domain.usecase

import com.mylearnings.core.util.Resource
import com.mylearnings.tracker_domain.model.TrackableFood
import com.mylearnings.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class SearchFoodUseCaseImpl @Inject constructor(
    private val trackerRepository: TrackerRepository
) : SearchFoodUseCase {
    override suspend fun invoke(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<List<TrackableFood>> {
        if (query.isBlank()) {
            return Resource.Success(data = emptyList())
        }
        return trackerRepository.searchFood(query.trim(), page, pageSize)
    }
}