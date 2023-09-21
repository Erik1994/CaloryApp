package com.mylearnings.tracker_presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.domain.usecase.FilterOutDigitsUseCase
import com.mylearnings.core.util.Resource
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core.util.UiText
import com.mylearnings.tracker_domain.usecase.TrackerUseCases
import com.mylearnings.core.R
import com.mylearnings.tracker_presentation.common.TrackerBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigitsUseCase: FilterOutDigitsUseCase
) : TrackerBaseViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
            }

            is SearchEvent.OnAmountFoodChange -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {
                        if (it.food == event.food) {
                            it.copy(amount = filterOutDigitsUseCase(event.amount))
                        } else it
                    }
                )
            }

            is SearchEvent.OnSearch -> {
                executeSearch()
            }

            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {
                        if (it.food == event.food) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }

            is SearchEvent.OnSearchFocusChanged -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }

            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                trackableFoods = emptyList()
            )
            when (val result = trackerUseCases.searchFoodUseCase(state.query)) {
                is Resource.Error -> {
                    state = state.copy(isSearching = false)
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }

                is Resource.Success -> {
                    state = state.copy(
                        trackableFoods = result.data.map {
                            TrackableFoodUiState(it)
                        },
                        isSearching = false
                    )
                }

                is Resource.NoInternetConnection -> {
                    state = state.copy(isSearching = false)
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            UiText.StringResource(R.string.internet_connection_error)
                        )
                    )
                }

                is Resource.Loading -> {

                }
            }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val uiState = state.trackableFoods.find { it.food == event.food }
            trackerUseCases.trackFoodUseCase(
                trackableFood = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )
            sendUiEvent(UiEvent.NavigateUp)
        }
    }
}