package com.tarunlahrod.androidforge.auth

import com.tarunlahrod.androidforge.network.TokenProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface SessionState {
    data object Restoring : SessionState
    data object LoggedIn : SessionState
    data object LoggedOut : SessionState
}

class AuthSession(private val tokenProvider: TokenProvider) {

    private val _state = MutableStateFlow<SessionState>(SessionState.Restoring)
    val state: StateFlow<SessionState> = _state.asStateFlow()

    suspend fun restore() {
        tokenProvider.initialize()

        _state.value = if (tokenProvider.getAccessToken() != null) {
            SessionState.LoggedIn
        } else {
            SessionState.LoggedOut
        }
    }
}