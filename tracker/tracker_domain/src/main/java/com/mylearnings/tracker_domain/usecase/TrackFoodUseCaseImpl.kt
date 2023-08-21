package com.mylearnings.tracker_domain.usecase

import com.mylearnings.tracker_domain.model.MealType
import com.mylearnings.tracker_domain.model.TrackableFood
import com.mylearnings.tracker_domain.model.TrackedFood
import com.mylearnings.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class TrackFoodUseCaseImpl @Inject constructor(
    private val trackerRepository: TrackerRepository
) : TrackFoodUseCase{
    override suspend operator fun invoke(
        trackableFood: TrackableFood,
        amount: Int,
        mealType: MealType,
        date: LocalDate
    ) {
        trackerRepository.insertTrackedFood(
            TrackedFood(
                name = trackableFood.name,
                carbs = ((trackableFood.carbsPer100g / 100f) * amount).roundToInt(),
                protein = ((trackableFood.proteinPer100g / 100f) * amount).roundToInt(),
                calories = ((trackableFood.caloriesPer100g / 100f) * amount).roundToInt(),
                fat = ((trackableFood.fatPer100g / 100f) * amount).roundToInt(),
                imageUrl = trackableFood.imageUrl,
                type = mealType,
                date = date,
                amount = amount
            )
        )
    }
}