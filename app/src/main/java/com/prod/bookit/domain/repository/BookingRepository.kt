package com.prod.bookit.domain.repository

import java.time.LocalDate
import java.time.LocalTime

interface BookingRepository {

    suspend fun book(
        spotId: String, timeFrom: LocalTime,
        timeUntil: LocalTime, date: LocalDate
    ): Boolean
}