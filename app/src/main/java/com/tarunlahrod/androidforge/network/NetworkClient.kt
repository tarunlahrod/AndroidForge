package com.tarunlahrod.androidforge.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient(
    private val gson: Gson,
    private val baseUrl: String
) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            GsonConverterFactory.create(gson)
        )
        .build()

    fun <T> createApi(service: Class<T>): T {
        return retrofit.create(service)
    }
}