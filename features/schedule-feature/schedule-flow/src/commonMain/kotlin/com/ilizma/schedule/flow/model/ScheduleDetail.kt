package com.ilizma.schedule.flow.model

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDetail(
    val id: Int,
    val name: String,
)