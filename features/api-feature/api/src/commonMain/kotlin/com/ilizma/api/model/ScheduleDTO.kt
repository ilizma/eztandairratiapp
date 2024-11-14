package com.ilizma.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDTO(
    @SerialName(value = "data") val schedule: List<ProgramDTO>? = null,
)