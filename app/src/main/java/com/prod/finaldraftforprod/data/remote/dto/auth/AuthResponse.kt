package com.prod.finaldraftforprod.data.remote.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String
)
