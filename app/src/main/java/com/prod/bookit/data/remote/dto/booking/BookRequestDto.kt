package com.prod.bookit.data.remote.dto.booking

import com.google.gson.annotations.SerializedName
import com.prod.bookit.presentation.util.serializeDateAndTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

data class BookRequestDto(
    @SerializedName("spot_id")
    val spotId: String,
    @SerializedName("time_from")
    val timeFrom: String,
    @SerializedName("time_until")
    val timeUntil: String
) {

    companion object {
        fun create(
            spotId: String, timeFrom: LocalTime,
            timeUntil: LocalTime, date: LocalDate
        ) = BookRequestDto(
            spotId = spotId,
            timeFrom = serializeDateAndTime(timeFrom, date),
            timeUntil = serializeDateAndTime(timeUntil, date)
        )
    }
}