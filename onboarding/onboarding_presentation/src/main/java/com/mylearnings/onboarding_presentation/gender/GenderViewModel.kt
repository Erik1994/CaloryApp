package com.mylearnings.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.data.model.Gender
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core_ui.navigation.UiEvent
import com.mylearnings.core_ui.navigation.Route
import com.mylearnings.onboarding_presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    preferences: Preferences
) : BaseViewModel(preferences) {
    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            sendUiEvent(UiEvent.Navigate(Route.AGE))
        }
    }
}