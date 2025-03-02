package com.prod.bookit.data.mappers

import com.prod.bookit.data.remote.dto.coworkings.CoworkingDetailDto
import com.prod.bookit.data.remote.dto.coworkings.CoworkingSummaryDto
import com.prod.bookit.domain.model.CoworkingDetail
import com.prod.bookit.domain.model.CoworkingSummary

fun CoworkingSummaryDto.toDomain(): CoworkingSummary =
    CoworkingSummary(
        id = id,
        name = name,
        address = address,
        opensAt = openingTime,
        endsAt = closingTime
    )

fun CoworkingDetailDto.toDomain(): CoworkingDetail =
    CoworkingDetail(
        id = id,
        name = name,
        description = description,
        address = address,
        capacity = capacity,
        opensAt = opensAt,
        endsAt = endsAt,
        images = images
    )
