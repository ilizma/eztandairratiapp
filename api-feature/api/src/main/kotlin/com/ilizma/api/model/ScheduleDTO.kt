package com.ilizma.api.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
@JvmInline
value class ScheduleDTO(
    @Json(name = "data") val schedule: List<ProgramDTO>? = null,
)