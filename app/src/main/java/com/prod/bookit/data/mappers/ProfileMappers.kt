package com.prod.bookit.data.mappers

import com.prod.bookit.data.remote.dto.profile.ProfileBookingDto
import com.prod.bookit.data.remote.dto.profile.UserProfileDto
import com.prod.bookit.domain.model.ProfileBookingModel
import com.prod.bookit.domain.model.UserProfile

fun UserProfileDto.toDomain(): UserProfile =
    UserProfile(
        id = id,
        isBusiness = isBusiness,
        avatarUrl = avatarUrl,
        email = email,
        fullName = fullName
    )

fun ProfileBookingDto.toDomain(): ProfileBookingModel =
    ProfileBookingModel(
        id = this.id,
        userId = this.userId,
        spotId = this.coworking.spot.id,
        timeFrom = this.timeFrom,
        timeUntil = this.timeUntil,
        status = this.status,
        title = this.coworking.spot.name,
        address = this.coworking.address,
        opensAt = this.coworking.opensAt,
        closesAt = this.coworking.closesAt
    )

