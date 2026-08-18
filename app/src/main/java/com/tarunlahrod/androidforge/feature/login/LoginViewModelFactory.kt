package com.tarunlahrod.androidforge.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tarunlahrod.androidforge.auth.AuthSession

class LoginViewModelFactory(
    private val repository: AuthRepository,
    private val authSession: AuthSession
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, authSession) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}