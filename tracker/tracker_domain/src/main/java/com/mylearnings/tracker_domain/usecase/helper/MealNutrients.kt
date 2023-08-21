package com.mylearnings.tracker_domain.usecase.helper

import com.mylearnings.tracker_domain.model.MealType

data class MealNutrients(
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val calories: Int,
    val mealType: MealType
)
