package com.prod.bookit.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

class RescheduleBookingRequest(
    @SerializedName("time_from") val timeFrom: String,
    @SerializedName("time_until") val timeUntil: String
)