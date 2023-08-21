package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.TrackedFood
import com.mylearnings.tracker_domain.repository.TrackerRepository
import javax.inject.Inject

class DeleteFoodUseCaseImpl @Inject constructor(
    private val trackerRepository: TrackerRepository
) : DeleteFoodUseCase {
    override suspend fun invoke(trackedFood: TrackedFood) =
        trackerRepository.deleteTrackedFood(trackedFood)
}