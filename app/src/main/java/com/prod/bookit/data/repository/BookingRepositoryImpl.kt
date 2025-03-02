package com.prod.bookit.data.repository

import com.prod.bookit.common.AppDispatchers
import com.prod.bookit.data.remote.api.BookingApi
import com.prod.bookit.data.remote.dto.booking.BookRequestDto
import com.prod.bookit.domain.repository.BookingRepository
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalTime

class BookingRepositoryImpl(
    private val api: BookingApi,
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

}