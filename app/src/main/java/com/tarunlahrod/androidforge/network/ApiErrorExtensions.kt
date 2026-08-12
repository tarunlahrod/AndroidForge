package com.tarunlahrod.androidforge.network

import com.google.gson.Gson
import java.io.IOException
import retrofit2.HttpException

fun Throwable.toApiError(): ApiError {
    return when (this) {
        is HttpException -> {
            val serverError = try {
                response()
                    ?.errorBody()
                    ?.string()
                    ?.let {
                        Gson().fromJson(
                            it,
                            ServerErrorResponse::class.java
                        )
                    }
            } catch (e: Exception) {
                null
            }

            ApiError(
                type = when (code()) {
                    in 400..499 -> ApiErrorType.Client
                    in 500..599 -> ApiErrorType.Server
                    else -> ApiErrorType.Unknown
                },
                httpCode = code(),
                code = serverError?.code,
                message = serverError?.message
            )
        }

        is IOException -> ApiError(
            type = ApiErrorType.Network
        )

        else -> ApiError(
            type = ApiErrorType.Unknown
        )
    }
}