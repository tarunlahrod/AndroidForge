package com.tarunlahrod.androidforge.di

import android.content.Context
import com.google.gson.Gson
import com.tarunlahrod.androidforge.auth.AuthSession
import com.tarunlahrod.androidforge.feature.login.AuthApi
import com.tarunlahrod.androidforge.feature.login.AuthRepository
import com.tarunlahrod.androidforge.network.DataStoreTokenProvider
import com.tarunlahrod.androidforge.network.NetworkClient

/**
 * This class knows how to construct and own application-wide dependencies.
 */
class AppContainer(
    private val context: Context
) {

    val gson: Gson by lazy { Gson() }

    val tokenProvider: DataStoreTokenProvider by lazy {
        DataStoreTokenProvider(context)
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

    val authSession: AuthSession by lazy {
        AuthSession(tokenProvider)
    }
}