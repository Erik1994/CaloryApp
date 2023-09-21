package com.mylearnings.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.UiEvent
import com.mylearnings.onboarding_presentation.common.OnBoardingBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    preferences: Preferences
) : OnBoardingBaseViewModel(preferences) {
    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    fun onActivityLevelChanged(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }

    override fun onNextClick() {
        preferences.saveActivityLevel(selectedActivityLevel)
        sendUiEvent(UiEvent.Success)
    }
}