package com.mylearnings.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.data.model.Gender
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core_ui.navigation.UiEvent
import com.mylearnings.onboarding_presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    preferences: Preferences
) : BaseViewModel(preferences) {
    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set

    fun onActivityLevelChanged(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }

    override fun onNextClick(route: String) {
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivityLevel)
            sendUiEvent(UiEvent.Navigate(route))
        }
    }
}