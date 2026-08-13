package com.tarunlahrod.androidforge.network

class InMemoryTokenProvider : TokenProvider {

    private var accessToken: String? = null

    override fun getAccessToken(): String? {
        return accessToken
    }

    override fun saveAccessToken(token: String) {
        accessToken = token
    }

    override fun clearAccessToken() {
        accessToken = null
    }
}