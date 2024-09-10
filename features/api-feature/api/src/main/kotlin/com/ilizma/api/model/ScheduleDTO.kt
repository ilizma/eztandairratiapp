package com.ilizma.api.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ScheduleDTO(
    @SerialName(value = "data") val schedule: List<ProgramDTO>? = null,
)