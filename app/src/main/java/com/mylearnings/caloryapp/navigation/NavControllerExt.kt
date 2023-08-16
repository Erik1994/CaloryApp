package com.mylearnings.caloryapp.navigation

import androidx.navigation.NavController
import com.mylearnings.core.util.UiEvent

fun NavController.navigate(uiEvent: UiEvent.Navigate) {
    navigate(uiEvent.route)
}