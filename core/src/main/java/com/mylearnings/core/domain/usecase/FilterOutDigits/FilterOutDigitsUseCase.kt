package com.mylearnings.core.domain.usecase.FilterOutDigits

interface FilterOutDigitsUseCase {
    operator fun invoke(text: String): String
}