package com.mylearnings.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.R
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core.util.UiText
import com.mylearnings.onboarding_presentation.common.OnBoardingBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    preferences: Preferences,
) : OnBoardingBaseViewModel(preferences) {
    var weight by mutableStateOf(DEFAULT_WEIGHT)
        private set

    fun onWeightEnter(weight: String) {
        if (weight.length <= MAX_WEIGHT_SYMBOL_COUNT) {
            this.weight = weight
        }
    }

    override fun onNextClick(route: String) {
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: run {
                sendUiEvent(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_weight_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveWeight(weightNumber)
            sendUiEvent(UiEvent.Navigate(route))
        }
    }

    private companion object {
        const val MAX_WEIGHT_SYMBOL_COUNT = 5
        const val DEFAULT_WEIGHT = "80.0"
    }
}