package com.mylearnings.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.R
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.domain.usecase.FilterOutDigits.FilterOutDigitsUseCase
import com.mylearnings.core_ui.navigation.Route
import com.mylearnings.core_ui.navigation.UiEvent
import com.mylearnings.core_ui.util.UiText
import com.mylearnings.onboarding_presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : BaseViewModel(preferences) {
    var age by mutableStateOf(DEFAULT_AGE)
        private set

    fun onAgeEnter(age: String) {
        if (age.length <= 3) {
            this.age = filterOutDigitsUseCase(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: run {
                sendUiEvent(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            sendUiEvent(UiEvent.Navigate(Route.HEIGHT))
        }
    }

    private companion object {
        const val DEFAULT_AGE = "20"
    }
}