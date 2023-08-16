package com.mylearnings.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.data.model.GoalType
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core_ui.navigation.UiEvent
import com.mylearnings.onboarding_presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    preferences: Preferences
) : BaseViewModel(preferences) {
    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    fun onGoalTypeChanged(goalType: GoalType) {
        selectedGoal = goalType
    }

    override fun onNextClick(route: String) {
        viewModelScope.launch {
            preferences.saveGaolType(selectedGoal)
            sendUiEvent(UiEvent.Navigate(route))
        }
    }
}