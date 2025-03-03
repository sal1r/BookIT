package com.prod.bookit.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

data class ProfileBookingDto(
    val id: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("time_from") val timeFrom: String,
    @SerializedName("time_until") val timeUntil: String,
    @SerializedName("status") val status: String,
    val coworking: CoworkingWithSpotDto
)
