package com.tarunlahrod.androidforge.feature.login

import com.tarunlahrod.androidforge.network.ApiResult
import com.tarunlahrod.androidforge.network.TokenProvider
import com.tarunlahrod.androidforge.network.safeApiCall

class AuthRepository(
    private val api: AuthApi,
    private val tokenProvider: TokenProvider
) {
    suspend fun login(
        email: String,
        password: String
    ): ApiResult<LoginResponse> {
        return safeApiCall {
            val response = api.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )

            tokenProvider.saveAccessToken(response.accessToken)

            response
        }
    }
}