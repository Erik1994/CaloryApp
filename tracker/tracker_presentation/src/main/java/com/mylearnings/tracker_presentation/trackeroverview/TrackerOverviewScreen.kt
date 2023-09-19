package com.mylearnings.tracker_presentation.trackeroverview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core_ui.dimension.LocalSpacing
import com.mylearnings.core.R
import com.mylearnings.tracker_presentation.trackeroverview.components.AddButton
import com.mylearnings.tracker_presentation.trackeroverview.components.DaySelector
import com.mylearnings.tracker_presentation.trackeroverview.components.ExpandableMeal
import com.mylearnings.tracker_presentation.trackeroverview.components.NutrientsHeader
import com.mylearnings.tracker_presentation.trackeroverview.components.TrackedFoodItem

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.navigationEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> onNavigate(uiEvent)
                else -> Unit
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = state.date,
                onPreviousDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
                onNextDayClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        items(
            state.meals,
            key = { meal -> meal.name.asString(context) }
        ) { meal ->
            ExpandableMeal(
                meal = meal,
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall)
                    ) {
                        state.trackedFoods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClick = {
                                    viewModel.onEvent(
                                        TrackerOverviewEvent.OnDeleteTrackedFoodClick(
                                            food
                                        )
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }
                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = { viewModel.onEvent(TrackerOverviewEvent.OnAddFoodClick(meal)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal)) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}