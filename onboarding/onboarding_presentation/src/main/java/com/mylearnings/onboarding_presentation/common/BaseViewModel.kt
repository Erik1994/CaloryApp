package com.mylearnings.onboarding_presentation.common

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mylearnings.core.data.preferences.Preferences
import com.mylearnings.core_ui.navigation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
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
}