package com.tarunlahrod.androidforge.network

data class ApiError(
    val type: ApiErrorType,
    val httpCode: Int? = null,
    val code: String? = null,
    val message: String? = null
)