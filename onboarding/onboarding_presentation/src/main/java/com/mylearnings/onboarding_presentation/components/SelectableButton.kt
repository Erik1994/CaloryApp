package com.mylearnings.onboarding_presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mylearnings.core_ui.dimension.LocalSpacing

@Composable
fun SelectableButton(
    modifier: Modifier = Modifier,
    @StringRes buttonText: Int,
    onClick: () -> Unit,
    color: Color,
    selectedTextColor: Color,
    isSelected: Boolean,
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(100.dp)
            )
            .background(
                color = if (isSelected) color else Color.Transparent,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable {
                onClick()
            }
            .padding(LocalSpacing.current.spaceMedium)
    ) {
        Text(
            text = stringResource(id = buttonText),
            style = textStyle,
            color = if (isSelected) selectedTextColor else color
        )
    }
}