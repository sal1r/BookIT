package com.prod.bookit.data.remote.dto.coworkings

import com.google.gson.annotations.SerializedName

data class TimeSlot(
    @SerializedName("time_from") val timeFrom: String,
    @SerializedName("time_until") val timeUntil: String
)
