package com.prod.bookit.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    val email: String,
    @SerializedName("full_name") val fullName: String,
    val password: String
)
