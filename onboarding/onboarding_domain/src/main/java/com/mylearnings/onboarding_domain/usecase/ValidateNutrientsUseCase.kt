package com.mylearnings.onboarding_domain.usecase

import com.mylearnings.core.util.UiText

interface ValidateNutrientsUseCase {
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): Result
}

sealed class Result {
    data class Success(
        val carbsRatio: Float,
        val proteinRatio: Float,
        val fatRatio: Float
    ) : Result()

    data class Error(val message: UiText) : Result()
}