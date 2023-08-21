package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.MealType
import com.mylearnings.tracker_domain.model.TrackableFood
import java.time.LocalDate

interface TrackFoodUseCase {
    suspend operator fun invoke(
        trackableFood: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    )
}