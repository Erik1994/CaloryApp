package com.mylearnings.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mylearnings.core.data.model.GoalType
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.UiEvent
import com.mylearnings.onboarding_presentation.common.OnBoardingBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    preferences: Preferences
) : OnBoardingBaseViewModel(preferences) {
    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    fun onGoalTypeChanged(goalType: GoalType) {
        selectedGoal = goalType
    }

    override fun onNextClick(route: String) {
        preferences.saveGaolType(selectedGoal)
        sendUiEvent(UiEvent.Navigate(route))
    }
}