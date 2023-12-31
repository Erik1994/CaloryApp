package com.mylearnings.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mylearnings.core.data.model.Gender
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.UiEvent
import com.mylearnings.onboarding_presentation.common.OnBoardingBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    preferences: Preferences
) : OnBoardingBaseViewModel(preferences) {
    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    fun onGenderClick(gender: Gender) {
        selectedGender = gender
    }

    override fun onNextClick() {
        preferences.saveGender(selectedGender)
        sendUiEvent(UiEvent.Success)
    }
}