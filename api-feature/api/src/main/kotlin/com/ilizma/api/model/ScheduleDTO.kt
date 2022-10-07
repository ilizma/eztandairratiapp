package com.ilizma.api.model

import com.squareup.moshi.Json

@JvmInline
value class ScheduleDTO(
    @Json(name = "data") val schedule: List<ProgramDTO>? = null,
)