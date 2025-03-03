package com.prod.bookit.data.mappers

import com.prod.bookit.data.remote.dto.coworkings.SpotDto
import com.prod.bookit.domain.model.BookObjectUIData

fun SpotDto.toDomain(): BookObjectUIData = BookObjectUIData(
    id = id,
    position = position,
    avalibleToBook = status == "active"
)