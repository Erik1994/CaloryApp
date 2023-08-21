package com.mylearnings.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.domain.usecase.FilterOutDigitsUseCase
import com.mylearnings.core.util.UiEvent
import com.mylearnings.onboarding_domain.usecase.Result
import com.mylearnings.onboarding_domain.usecase.ValidateNutrientsUseCase
import com.mylearnings.onboarding_presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NutrientViewModel @Inject constructor(
    preferences: Preferences,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase,
    private val validateNutrients: ValidateNutrientsUseCase
) : BaseViewModel(preferences) {
    var state by mutableStateOf(NutrientGoalState())
        private set

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> state =
                state.copy(carbsRatio = filterOutDigitsUseCase(event.ratio))

            is NutrientGoalEvent.OnProteinRatioEnter -> state =
                state.copy(proteinRatio = filterOutDigitsUseCase(event.ratio))

            is NutrientGoalEvent.OnFatRatioEnter -> state =
                state.copy(fatRatio = filterOutDigitsUseCase(event.ratio))

            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    carbsRatioText = state.carbsRatio,
                    fatRatioText = state.fatRatio,
                    proteinRatioText = state.proteinRatio
                )
                when(result) {
                    is Result.Success -> {
                        preferences.saveFatRatio(result.fatRatio)
                        preferences.saveCarbsRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        onNextClick(event.route)
                    }
                    is Result.Error -> {
                        sendUiEvent(UiEvent.ShowSnackBar(result.message))
                    }
                }

            }
        }
    }

    override fun onNextClick(route: String) {
        sendUiEvent(UiEvent.Navigate(route))
    }
}