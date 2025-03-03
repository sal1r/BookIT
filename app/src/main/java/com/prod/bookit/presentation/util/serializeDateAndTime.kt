package com.prod.bookit.presentation.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun serializeDateAndTime(time: LocalTime, date: LocalDate): String =
    ZonedDateTime.of(LocalDateTime.of(date, time), ZoneId.systemDefault()).format(
        DateTimeFormatter.ISO_OFFSET_DATE_TIME
    )