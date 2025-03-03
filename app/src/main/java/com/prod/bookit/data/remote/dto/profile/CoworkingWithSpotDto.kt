package com.prod.bookit.data.remote.dto.profile

import com.google.gson.annotations.SerializedName

data class CoworkingWithSpotDto(
    val address: String,
    @SerializedName("opens_at") val opensAt: String,
    @SerializedName("closes_at") val closesAt: String,
    val spot: SpotInfoDto
)
