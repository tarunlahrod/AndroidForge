package com.tarunlahrod.androidforge.network

import kotlinx.coroutines.CancellationException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {
        ApiResult.Success(
            data = apiCall()
        )
    } catch (e: CancellationException) {
        throw e
    } catch (throwable: Throwable) {
        ApiResult.Failure(
            error = throwable.toApiError()
        )
    }
}
