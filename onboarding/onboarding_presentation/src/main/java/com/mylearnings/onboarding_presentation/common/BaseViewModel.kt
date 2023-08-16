package com.mylearnings.onboarding_presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    protected val preferences: Preferences
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val navigationEvent = _uiEvent.receiveAsFlow()

    fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }

    abstract fun onNextClick(route: String)
}