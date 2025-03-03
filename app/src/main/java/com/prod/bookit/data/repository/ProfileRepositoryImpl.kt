package com.prod.bookit.data.repository

import com.prod.bookit.data.mappers.toDomain
import com.prod.bookit.data.remote.api.ProfileApi
import com.prod.bookit.domain.model.ProfileBookingModel
import com.prod.bookit.domain.model.UserProfile
import com.prod.bookit.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val api: ProfileApi
): ProfileRepository {
    override suspend fun getProfile(): UserProfile {
        val profile = api.getProfile()

        return profile.toDomain()
    }

    override suspend fun getBookings(): List<ProfileBookingModel> {
        return api.getBookings().map { it.toDomain() }
    }

    override suspend fun deleteBooking(id: String) {

    }
}