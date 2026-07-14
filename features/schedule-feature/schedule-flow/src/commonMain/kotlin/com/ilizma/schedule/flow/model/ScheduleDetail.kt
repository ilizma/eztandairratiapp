package com.ilizma.schedule.flow.model

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleDetail(
    val id: Int,
    val name: String,
) : NavKey
