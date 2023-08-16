package com.mylearnings.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.R
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.domain.usecase.FilterOutDigits.FilterOutDigitsUseCase
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core.util.UiText
import com.mylearnings.onboarding_presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : BaseViewModel(preferences) {
    var height by mutableStateOf(DEFAULT_HEIGHT)
        private set

    fun onHeightEnter(height: String) {
        if (height.length <= MAX_HEIGHT_SYMBOL_COUNT) {
            this.height = filterOutDigitsUseCase(height)
        }
    }

    override fun onNextClick(route: String) {
        val heightNumber = height.toIntOrNull() ?: run {
            sendUiEvent(
                UiEvent.ShowSnackBar(
                    UiText.StringResource(R.string.error_height_cant_be_empty)
                )
            )
            return
        }
        preferences.saveHeight(heightNumber)
        sendUiEvent(UiEvent.Navigate(route))
    }

    private companion object {
        const val MAX_HEIGHT_SYMBOL_COUNT = 3
        const val DEFAULT_HEIGHT = "180"
    }
}