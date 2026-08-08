package com.tarunlahrod.androidforge.feature.counter

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState.asStateFlow()

    fun increment() {
        _uiState.update { currentState ->
            currentState.copy(
                count = currentState.count + 1
            )
        }
    }

    fun decrement() {
        _uiState.update { currentState ->
            currentState.copy(
                count = currentState.count - 1
            )
        }
    }

    fun reset() {
        _uiState.value = CounterUiState()
    }
}