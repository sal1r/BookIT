package com.prod.bookit.data.repository

import com.prod.bookit.data.mappers.toDomain
import com.prod.bookit.data.remote.api.ProfileApi
import com.prod.bookit.data.remote.dto.coworkings.AvailableSlotsResponse
import com.prod.bookit.data.remote.dto.coworkings.TimeSlot
import com.prod.bookit.data.remote.dto.profile.BookingWithOptionsDto
import com.prod.bookit.data.remote.dto.profile.RescheduleBookingRequest
import com.prod.bookit.domain.model.ProfileBookingModel
import com.prod.bookit.domain.model.UserProfile
import com.prod.bookit.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val api: ProfileApi
): ProfileRepository {
    override suspend fun getProfile(): UserProfile {

        try {
            val profile = api.getProfile()
            return profile.toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
            return UserProfile(
                "1",
                 "Неизвестно",
                 "",
                false,
                "Неизвестно"
            )
        }

    }

    override suspend fun getBookings(): List<ProfileBookingModel> {
        return api.getBookings().map { it.toDomain() }
    }

    override suspend fun deleteBooking(id: String): List<ProfileBookingModel> {
        return try {
            api.cancelBooking(id)
            getBookings()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun rescheduleBooking(
        bookingId: String,
        newTimeFrom: String,
        newTimeUntil: String
    ): BookingWithOptionsDto {
        return try {
            val request = RescheduleBookingRequest(
                timeFrom = newTimeFrom,
                timeUntil = newTimeUntil
            )
            api.rescheduleBooking(bookingId, request)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}