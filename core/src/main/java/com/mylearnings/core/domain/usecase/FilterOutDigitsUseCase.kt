package com.mylearnings.core.domain.usecase

interface FilterOutDigitsUseCase {
    operator fun invoke(text: String): String
}