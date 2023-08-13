package com.mylearnings.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mylearnings.core.R
import com.mylearnings.core_ui.dimension.LocalSpacing
import com.mylearnings.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier) {
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier.padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,

        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            buttonText = R.string.next, onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}