package com.tarunlahrod.androidforge.network

interface TokenProvider {
    fun getAccessToken(): String?
    suspend fun initialize()
    suspend fun saveAccessToken(token: String)
    suspend fun clearAccessToken()
}