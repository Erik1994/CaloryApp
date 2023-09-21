package com.mylearnings.tracker_presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class TrackerBaseViewModel() : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val navigationEvent = _uiEvent.receiveAsFlow()

    fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}