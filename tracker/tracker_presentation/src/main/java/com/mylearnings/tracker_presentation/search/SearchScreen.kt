package com.mylearnings.tracker_presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core_ui.dimension.LocalSpacing
import com.mylearnings.core.R
import com.mylearnings.tracker_domain.model.MealType
import com.mylearnings.tracker_presentation.search.component.SearchTextField
import com.mylearnings.tracker_presentation.search.component.TrackableFoodItem
import java.time.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    month: Int,
    day: Int,
    year: Int,
    mealName: String,
    onNavigateUp: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = keyboardController) {
        viewModel.navigationEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = uiEvent.message.asString(context)
                    )
                }

                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text = state.query,
            onValueChange = { viewModel.onEvent(SearchEvent.OnQueryChange(it)) },
            onSearch = { viewModel.onEvent(SearchEvent.OnSearch) },
            onFocusChanged = { viewModel.onEvent(SearchEvent.OnSearchFocusChanged(it.isFocused)) },
            showHint = state.isHintVisible
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.trackableFoods) { food ->
                TrackableFoodItem(
                    trackableFoodUiState = food,
                    onClick = {
                        viewModel.onEvent(SearchEvent.OnToggleTrackableFood(food = food.food))
                    },
                    onAmountChange = {
                        viewModel.onEvent(
                            SearchEvent.OnAmountFoodChange(
                                food = food.food,
                                amount = it
                            )
                        )
                    },
                    onTrack = {
                        viewModel.onEvent(
                            SearchEvent.OnTrackFoodClick(
                                food = food.food,
                                mealType = MealType.fromString(mealName),
                                date = LocalDate.of(year, month, day)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isSearching -> CircularProgressIndicator()
            state.trackableFoods.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}