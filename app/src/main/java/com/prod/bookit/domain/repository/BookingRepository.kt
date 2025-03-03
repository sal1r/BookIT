package com.prod.bookit.domain.repository

import com.prod.bookit.data.remote.dto.coworkings.AvailableSlotsResponse
import com.prod.bookit.domain.model.BookObjectUIData
import com.prod.bookit.presentation.models.BookingStatus
import java.time.LocalDate
import java.time.LocalTime

interface BookingRepository {

    suspend fun book(
        spotId: String, timeFrom: LocalTime,
        timeUntil: LocalTime, date: LocalDate
    ): BookingStatus

    suspend fun getSpotsForCoworking(
        coworkingId: String, timeFrom: LocalTime,
        timeUntil: LocalTime, date: LocalDate
    ): List<BookObjectUIData>
}