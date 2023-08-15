package com.mylearnings.caloryapp.navigation

import androidx.navigation.NavController
import com.mylearnings.core_ui.navigation.NavigationEvent

fun NavController.navigate(navigationEvent: NavigationEvent.Navigate) {
    navigate(navigationEvent.route)
}