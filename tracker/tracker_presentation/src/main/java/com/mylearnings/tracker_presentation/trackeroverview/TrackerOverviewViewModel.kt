package com.mylearnings.tracker_presentation.trackeroverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core_ui.navigation.Route
import com.mylearnings.onboarding_presentation.common.TrackerBaseViewModel
import com.mylearnings.tracker_domain.usecase.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) : TrackerBaseViewModel() {

    private var getFoodsForDateJob: Job? = null
    var state by mutableStateOf(TrackerOverviewState())
        private set

    init {
        preferences.saveShouldShowOnBoarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnAddFoodClick -> {
                sendUiEvent(
                    UiEvent.Navigate(
                        route = Route.SEARCH
                                + "/${event.meal.mealType.name}"
                                + "/${state.date.dayOfMonth}"
                                + "/${state.date.monthValue}"
                                + "/${state.date.year}"
                    )
                )
            }

            is TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1)
                )
                refreshFood()
            }

            is TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFood()
            }

            is TrackerOverviewEvent.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.name == event.meal.name) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }

            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteFoodUseCase(event.trackedFood)
                    refreshFood()
                }
            }
        }
    }

    private fun refreshFood() {
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases
            .getFoodForDateUseCase(state.date)
            .onEach { foods ->
                val nutrientsResult = trackerUseCases.calculateMealNutrientsUseCase(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalFat = nutrientsResult.totalFat,
                    totalClories = nutrientsResult.totalCalories,
                    totalProtein = nutrientsResult.totalProtein,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    trackedFoods = foods,
                    meals = state.meals.map {
                        val nutrientsForMeal =
                            nutrientsResult.mealNutrients[it.mealType] ?: return@map it.copy(
                                calories = 0,
                                carbs = 0,
                                fat = 0,
                                protein = 0
                            )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            calories = nutrientsForMeal.calories,
                            fat = nutrientsForMeal.fat,
                            protein = nutrientsForMeal.protein
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }
}