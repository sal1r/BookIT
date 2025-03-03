package com.prod.bookit.domain.model

data class UserProfile(
    val id: String,
    val fullName: String,
    val avatarUrl: String?,
    val isBusiness: Boolean,
    val email: String,
)