package com.tarunlahrod.androidforge.network

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.tokenDataStore by preferencesDataStore(
    name = "auth_preferences"
)

class DataStoreTokenProvider(
    private val context: Context
) : TokenProvider {

    private companion object {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    private var cachedToken: String? = null

    override suspend fun initialize() {
        cachedToken = context.tokenDataStore.data
            .first()[ACCESS_TOKEN]
    }

    override fun getAccessToken(): String? {
        return cachedToken
    }

    override suspend fun saveAccessToken(token: String) {
        cachedToken = token

        context.tokenDataStore.edit {
            it[ACCESS_TOKEN] = token
        }
    }

    override suspend fun clearAccessToken() {
        cachedToken = null

        context.tokenDataStore.edit {
            it.remove(ACCESS_TOKEN)
        }
    }
}