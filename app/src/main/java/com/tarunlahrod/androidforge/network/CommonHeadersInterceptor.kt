package com.tarunlahrod.androidforge.network

import okhttp3.Interceptor
import okhttp3.Response

class CommonHeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Client", "AndroidForge")
            .build()

        return chain.proceed(request)
    }
}