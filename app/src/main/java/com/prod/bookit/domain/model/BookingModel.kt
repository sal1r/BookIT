package com.prod.bookit.domain.model

data class BookingModel(
    val id: String,
    val userId: String,
    val spotId: String,
    val timeFrom: String,
    val timeUntil: String,
    val address: String,
    val opensAt: String,
    val closesAt: String
)
