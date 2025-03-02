package com.prod.bookit.data.mappers

import com.prod.bookit.data.remote.dto.profile.BookingDto
import com.prod.bookit.data.remote.dto.profile.UserProfileDto
import com.prod.bookit.domain.model.BookingModel
import com.prod.bookit.domain.model.UserProfile

fun UserProfileDto.toDomain(): UserProfile =
    UserProfile(
        id = id,
        avatar = avatar,
        email = email,
        name = name
    )

fun BookingDto.toDomain(): BookingModel =
    BookingModel(
        id = id,
        userId = userId,
        spotId = spotId,
        timeFrom = timeFrom,
        timeUntil = timeUntil,
        address = address,
        opensAt = opensAt,
        closesAt = closesAt
    )

