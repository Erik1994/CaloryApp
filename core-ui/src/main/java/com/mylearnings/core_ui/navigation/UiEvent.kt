package com.mylearnings.core_ui.navigation

import com.mylearnings.core_ui.util.UiText

sealed class UiEvent {
    class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}
