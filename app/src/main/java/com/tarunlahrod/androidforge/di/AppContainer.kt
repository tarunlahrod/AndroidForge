package com.tarunlahrod.androidforge.di

import com.google.gson.Gson
import com.tarunlahrod.androidforge.feature.login.AuthApi
import com.tarunlahrod.androidforge.feature.login.AuthRepository
import com.tarunlahrod.androidforge.feature.login.FakeAuthRepository
import com.tarunlahrod.androidforge.network.InMemoryTokenProvider
import com.tarunlahrod.androidforge.network.NetworkClient

/**
 * This class knows how to construct and own application-wide dependencies.
 */
class AppContainer {

//    val authRepository by lazy { FakeAuthRepository() }

    val gson: Gson by lazy { Gson() }

    val tokenProvider: InMemoryTokenProvider by lazy {
        InMemoryTokenProvider()
    }

    val networkClient: NetworkClient by lazy {
        NetworkClient(
            gson = gson,
            baseUrl = "https://example.com/",
            tokenProvider = tokenProvider
        )
    }

    val authApi: AuthApi by lazy {
        networkClient.createApi(AuthApi::class.java)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepository(
            api = authApi,
            tokenProvider = tokenProvider
        )
    }
}