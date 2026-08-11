package com.tarunlahrod.androidforge.feature.login

import com.tarunlahrod.androidforge.network.ApiResult
import com.tarunlahrod.androidforge.network.safeApiCall
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException

class FakeAuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): ApiResult<Unit> {
        return safeApiCall {
            fakeApiCall(email, password)
        }
    }
}

private suspend fun fakeApiCall(email: String, password: String): Unit {
    delay(2000)
    if (email != "admin@test.com" || password != "1234") {
        val errorBody = """
            {
                "code": "INVALID_CREDENTIALS",
                "message": "Invalid credentials"
            }
        """.trimIndent()

        throw HttpException(
            retrofit2.Response.error<Unit>(
                401,
                errorBody.toResponseBody("application/json".toMediaType())
            )
        )
    }
}