package com.prod.bookit.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

data class UserProfileDto(
    val id: String,
    val email: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("is_business") val isBusiness: String
)