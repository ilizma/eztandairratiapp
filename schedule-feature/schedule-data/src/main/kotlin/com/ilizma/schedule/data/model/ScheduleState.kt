package com.ilizma.schedule.data.model

import androidx.annotation.Keep

@Keep
sealed class ScheduleState {

    data class Success(
        val programList: List<Program>,
    ) : ScheduleState()

    data class Error(
        val message: String,
    ) : ScheduleState()

}