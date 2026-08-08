package com.tarunlahrod.androidforge.feature.login

import kotlinx.coroutines.delay

class FakeAuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Boolean {
        delay(2000)
        return email == "admin@test.com" && password == "1234"
    }
}