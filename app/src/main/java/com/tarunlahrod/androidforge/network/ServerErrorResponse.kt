package com.tarunlahrod.androidforge.network

import com.google.gson.annotations.SerializedName

data class ServerErrorResponse (
    @SerializedName("code") val code: String? = null,
    @SerializedName("message") val message: String? = null
)