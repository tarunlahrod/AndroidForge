package com.tarunlahrod.androidforge.network

class InMemoryTokenProvider : TokenProvider {

    private var accessToken: String? = null

    override fun getAccessToken(): String? {
        return accessToken
    }

    override suspend fun initialize() {
        // Nothing to restore for an in-memory implementation
    }

    override suspend fun saveAccessToken(token: String) {
        accessToken = token
    }

    override suspend fun clearAccessToken() {
        accessToken = null
    }
}