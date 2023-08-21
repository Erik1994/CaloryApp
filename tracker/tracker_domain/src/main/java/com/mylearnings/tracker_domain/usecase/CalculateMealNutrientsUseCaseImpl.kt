package com.mylearnings.tracker_domain.usecase

import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.data.model.Gender
import com.mylearnings.core.data.model.GoalType
import com.mylearnings.core.data.model.UserInfo
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.tracker_domain.model.TrackedFood
import com.mylearnings.tracker_domain.usecase.helper.MealNutrients
import com.mylearnings.tracker_domain.usecase.helper.Result
import kotlin.math.roundToInt

class CalculateMealNutrientsUseCaseImpl(
    private val preferences: Preferences
) : CalculateMealNutrientsUseCase {
    override fun invoke(trackedFoods: List<TrackedFood>): Result {
        val allNutrients = trackedFoods
            .groupBy { it.type }
            .mapValues { entry ->
                val type = entry.key
                val foods = entry.value
                MealNutrients(
                    carbs = foods.sumOf { it.carbs },
                    calories = foods.sumOf { it.calories },
                    fat = foods.sumOf { it.fat },
                    protein = foods.sumOf { it.protein },
                    mealType = type
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalFat = allNutrients.values.sumOf { it.fat }
        val totalProtein = allNutrients.values.sumOf { it.protein }
        val totalCalories = allNutrients.values.sumOf { it.calories }

        val userInfo = preferences.loadUserInfo()
        val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 4f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloryGoal,
            totalCarbs = totalCarbs,
            totalProtein = totalProtein,
            totalFat = totalFat,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when (userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }

            is Gender.Female -> {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when (userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when (userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }

}