package com.mylearnings.tracker_domain.usecase

data class TrackerUseCases(
    val trackFoodUseCase: TrackFoodUseCase,
    val searchFoodUseCase: SearchFoodUseCase,
    val deleteFoodUseCase: DeleteFoodUseCase,
    val getFoodForDateUseCase: GetFoodForDateUseCase,
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase
)
