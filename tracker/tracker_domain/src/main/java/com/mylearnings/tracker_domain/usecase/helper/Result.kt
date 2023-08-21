package com.mylearnings.tracker_domain.usecase.helper

import com.mylearnings.tracker_domain.model.MealType

data class Result(
    val carbsGoal: Int,
    val proteinGoal: Int,
    val fatGoal: Int,
    val caloriesGoal: Int,
    val totalCarbs: Int,
    val totalProtein: Int,
    val totalFat: Int,
    val totalCalories: Int,
    val mealNutrients: Map<MealType, MealNutrients>
)
