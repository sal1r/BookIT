package com.prod.bookit.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

data class BookingDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("spot_id")
    val spotId: String,

    @SerializedName("time_from")
    val timeFrom: String,

    @SerializedName("time_until")
    val timeUntil: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("opens_at")
    val opensAt: String,

    @SerializedName("closes_at")
    val closesAt: String
)
