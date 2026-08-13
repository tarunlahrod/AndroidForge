package com.tarunlahrod.androidforge.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(
    private val gson: Gson,
    private val baseUrl: String,
    private val tokenProvider: TokenProvider
) {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(CommonHeadersInterceptor())
        .addInterceptor(AuthInterceptor(tokenProvider))
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(gson)
        )
        .build()

    fun <T> createApi(service: Class<T>): T {
        return retrofit.create(service)
    }
}