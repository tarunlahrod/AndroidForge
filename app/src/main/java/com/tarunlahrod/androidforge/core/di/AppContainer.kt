package com.tarunlahrod.androidforge.core.di

import com.tarunlahrod.androidforge.feature.login.FakeAuthRepository

/**
 * This class knows how to construct and own application-wide dependencies.
 */
class AppContainer {

    val authRepository by lazy { FakeAuthRepository() }

}