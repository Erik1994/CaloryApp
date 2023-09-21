package com.mylearnings.onboarding_presentation.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.mylearnings.core.R
import com.mylearnings.core.data.model.GoalType
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core_ui.dimension.LocalSpacing
import com.mylearnings.onboarding_presentation.components.ActionButton
import com.mylearnings.onboarding_presentation.components.SelectableButton

@Composable
fun GoalScreen(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    buttonText = R.string.lose,
                    onClick = { viewModel.onGoalTypeChanged(GoalType.LoseWeight) },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGoal is GoalType.LoseWeight,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    buttonText = R.string.keep,
                    onClick = { viewModel.onGoalTypeChanged(GoalType.KeepWeight) },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGoal is GoalType.KeepWeight,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    buttonText = R.string.gain,
                    onClick = { viewModel.onGoalTypeChanged(GoalType.GainWeight) },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedGoal is GoalType.GainWeight,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        ActionButton(
            buttonText = R.string.next,
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )
    }
}