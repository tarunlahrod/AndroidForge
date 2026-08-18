package com.tarunlahrod.androidforge.feature.login

sealed interface LoginUiEvent {

    data class ShowToast(
        val message: String
    ) : LoginUiEvent
}