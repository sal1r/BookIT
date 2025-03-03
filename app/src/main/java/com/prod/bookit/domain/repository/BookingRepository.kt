package com.prod.bookit.domain.repository

import com.prod.bookit.domain.model.BookObjectUIData
import java.time.LocalDate
import java.time.LocalTime

interface BookingRepository {

    suspend fun book(
        spotId: String, timeFrom: LocalTime,
        timeUntil: LocalTime, date: LocalDate
    ): Boolean

    suspend fun getSpotsForCoworking(
        coworkingId: String, timeFrom: LocalTime,
        timeUntil: LocalTime, date: LocalDate
    ): List<BookObjectUIData>
}