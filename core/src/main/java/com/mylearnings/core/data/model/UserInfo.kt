package com.mylearnings.core.data.model

data class UserInfo(
    val age: Int,
    val weight: Float,
    val height: Int,
    val fatRatio: Float,
    val carbRatio: Float,
    val proteinRatio: Float,
    val gender: Gender,
    val goalType: GoalType,
    val activityLevel: ActivityLevel
)
