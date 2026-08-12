package com.tarunlahrod.androidforge.feature.login

import com.tarunlahrod.androidforge.network.ApiResult
import com.tarunlahrod.androidforge.network.safeApiCall

class AuthRepository(
    private val api: AuthApi
) {
    suspend fun login(
        email: String,
        password: String
    ): ApiResult<LoginResponse> {
        return safeApiCall {
            api.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
        }
    }
}