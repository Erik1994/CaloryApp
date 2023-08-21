package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.TrackedFood

interface DeleteFoodUseCase {
    suspend operator fun invoke(trackedFood: TrackedFood)
}