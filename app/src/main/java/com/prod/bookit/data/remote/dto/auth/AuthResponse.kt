package com.prod.bookit.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token") val token: String
)
