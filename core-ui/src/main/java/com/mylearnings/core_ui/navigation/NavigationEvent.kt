package com.mylearnings.core_ui.navigation

sealed class NavigationEvent {
    class Navigate(val route: String) : NavigationEvent()
    object NavigateUp : NavigationEvent()
}
