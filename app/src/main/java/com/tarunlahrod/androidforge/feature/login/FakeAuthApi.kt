package com.tarunlahrod.androidforge.feature.login

class FakeAuthApi : AuthApi {
    override suspend fun login(request: LoginRequest): LoginResponse {
        return LoginResponse(
            success = true,
            accessToken = "fake-token"
        )
    }

    override suspend fun getProfile() {

    }
}