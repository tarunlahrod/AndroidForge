package com.tarunlahrod.androidforge.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: FakeAuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<LoginUiEvent>()
    val uiEvent: SharedFlow<LoginUiEvent> = _uiEvent.asSharedFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                isLoginEnabled = calculateLoginEnabled(email, currentState.password)
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                isLoginEnabled = calculateLoginEnabled(currentState.email, password)
            )
        }
    }

    fun login() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val success = repository.login(
                email = uiState.value.email,
                password = uiState.value.password
            )

            if (success) {
                _uiEvent.emit(LoginUiEvent.NavigateToHome)
            } else {
                _uiEvent.emit(
                    LoginUiEvent.ShowToast("Invalid credentials")
                )
            }

            _uiState.update {
                it.copy(isLoading = false)
            }
        }
    }

    private fun calculateLoginEnabled(
        email: String,
        password: String
    ): Boolean {
        return email.isNotBlank() && password.isNotBlank()
    }

}