package com.tarunlahrod.androidforge.feature.login

sealed interface LoginUiEvent {

    data object NavigateToHome : LoginUiEvent

    data class ShowToast(
        val message: String
    ) : LoginUiEvent
}