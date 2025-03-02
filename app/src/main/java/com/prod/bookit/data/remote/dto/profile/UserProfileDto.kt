package com.prod.bookit.data.remote.dto.profile

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDto(
    val id: Int,
    val avatar: String,
    val email: String,
    val name: String
)