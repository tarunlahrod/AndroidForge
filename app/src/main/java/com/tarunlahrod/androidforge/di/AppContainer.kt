package com.tarunlahrod.androidforge.di

import com.google.gson.Gson
import com.tarunlahrod.androidforge.feature.login.FakeAuthRepository

/**
 * This class knows how to construct and own application-wide dependencies.
 */
class AppContainer {

    val authRepository by lazy { FakeAuthRepository() }

    val gson: Gson by lazy { Gson() }
}