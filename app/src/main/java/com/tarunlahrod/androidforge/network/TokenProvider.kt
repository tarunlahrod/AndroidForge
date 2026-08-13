package com.tarunlahrod.androidforge.network

interface TokenProvider {
    fun getAccessToken(): String?
    fun saveAccessToken(token: String)
    fun clearAccessToken()
}