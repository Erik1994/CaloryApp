package com.mylearnings.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mylearnings.core.R
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.domain.usecase.FilterOutDigitsUseCase
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core.util.UiText
import com.mylearnings.onboarding_presentation.common.OnBoardingBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : OnBoardingBaseViewModel(preferences) {
    var height by mutableStateOf(DEFAULT_HEIGHT)
        private set

    fun onHeightEnter(height: String) {
        if (height.length <= MAX_HEIGHT_SYMBOL_COUNT) {
            this.height = filterOutDigitsUseCase(height)
        }
    }

    override fun onNextClick() {
        val heightNumber = height.toIntOrNull() ?: run {
            sendUiEvent(
                UiEvent.ShowSnackBar(
                    UiText.StringResource(R.string.error_height_cant_be_empty)
                )
            )
            return
        }
        preferences.saveHeight(heightNumber)
        sendUiEvent(UiEvent.Success)
    }

    private companion object {
        const val MAX_HEIGHT_SYMBOL_COUNT = 3
        const val DEFAULT_HEIGHT = "180"
    }
}