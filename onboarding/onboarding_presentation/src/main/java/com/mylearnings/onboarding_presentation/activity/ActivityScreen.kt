package com.mylearnings.onboarding_presentation.activity

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
import com.mylearnings.core.data.model.ActivityLevel
import com.mylearnings.core.util.UiEvent
import com.mylearnings.core_ui.dimension.LocalSpacing
import com.mylearnings.onboarding_presentation.components.ActionButton
import com.mylearnings.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
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
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    buttonText = R.string.low,
                    onClick = { viewModel.onActivityLevelChanged(ActivityLevel.Low) },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.Low,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    buttonText = R.string.medium,
                    onClick = { viewModel.onActivityLevelChanged(ActivityLevel.Medium) },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.Medium,
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    buttonText = R.string.high,
                    onClick = { viewModel.onActivityLevelChanged(ActivityLevel.High) },
                    color = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = Color.White,
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.High,
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