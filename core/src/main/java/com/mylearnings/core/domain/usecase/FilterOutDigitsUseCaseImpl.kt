package com.mylearnings.core.domain.usecase

import javax.inject.Inject

class FilterOutDigitsUseCaseImpl @Inject constructor(): FilterOutDigitsUseCase {
    override fun invoke(text: String): String {
        return text.filter { it.isDigit() }
    }
}