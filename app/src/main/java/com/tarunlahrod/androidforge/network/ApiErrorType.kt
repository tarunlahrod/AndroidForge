package com.tarunlahrod.androidforge.network

sealed interface ApiErrorType {
    data object Network : ApiErrorType
    data object Client : ApiErrorType
    data object Server : ApiErrorType
    data object Unknown : ApiErrorType
}