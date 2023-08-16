package com.mylearnings.caloryapp.navigation

import androidx.navigation.NavController
import com.mylearnings.core_ui.navigation.UiEvent

fun NavController.navigate(uiEvent: UiEvent.Navigate) {
    navigate(uiEvent.route)
}