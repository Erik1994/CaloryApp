package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.TrackedFood
import com.mylearnings.tracker_domain.usecase.helper.Result

interface CalculateMealNutrientsUseCase {
    operator fun invoke(trackedFoods: List<TrackedFood>): Result
}