package com.prod.bookit.data.repository

import com.prod.bookit.common.AppDispatchers
import com.prod.bookit.data.mappers.toDomain
import com.prod.bookit.data.remote.api.BookingApi
import com.prod.bookit.data.remote.api.CoworkingsApi
import com.prod.bookit.data.remote.dto.booking.BookRequestDto
import com.prod.bookit.data.remote.dto.coworkings.SpotDto
import com.prod.bookit.domain.model.BookObjectUIData
import com.prod.bookit.domain.repository.BookingRepository
import com.prod.bookit.presentation.util.serializeDateAndTime
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime

class BookingRepositoryImpl(
    private val api: BookingApi,
    private val coworkingsApi: CoworkingsApi,
    private val dispatchers: AppDispatchers
) : BookingRepository {

    override suspend fun book(
        spotId: String,
        timeFrom: LocalTime,
        timeUntil: LocalTime,
        date: LocalDate
    ): Boolean = withContext(dispatchers.io) {
        api.book(BookRequestDto.create(spotId, timeFrom, timeUntil, date)).isSuccessful
    }

    override suspend fun getSpotsForCoworking(
        coworkingId: String,
        timeFrom: LocalTime,
        timeUntil: LocalTime,
        date: LocalDate
    ): List<BookObjectUIData> = withContext(dispatchers.io) {
        coworkingsApi.getSpots(
            coworkingId = coworkingId,
            timeFrom = serializeDateAndTime(timeFrom, date),
            timeUntil = serializeDateAndTime(timeUntil, date)
        ).map(SpotDto::toDomain)
    }
}