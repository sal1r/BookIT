package com.prod.bookit.presentation.models

import java.time.LocalDate
import java.time.LocalTime

data class BookingData(
    val spotId: String,
    val coworkingName: String,
    val bookObjectIndex: Int,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val date: LocalDate
)
