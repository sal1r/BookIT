package com.prod.bookit.data.remote.dto.coworkings

import com.google.gson.annotations.SerializedName

data class AvailableSlotsResponse(
    @SerializedName("available_slots") val availableSlots: List<TimeSlot>
)
