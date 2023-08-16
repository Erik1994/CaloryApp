package com.mylearnings.core.util

sealed class UiEvent {
    class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}
