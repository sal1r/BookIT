package com.prod.bookit.domain.repository

import com.prod.bookit.domain.model.BookingModel
import com.prod.bookit.domain.model.UserProfile

interface ProfileRepository {
    suspend fun getProfile(): UserProfile

    suspend fun getBookings(): List<BookingModel>

    suspend fun deleteBooking(id: String)
}