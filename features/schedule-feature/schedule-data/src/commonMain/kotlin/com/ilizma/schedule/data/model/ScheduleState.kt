package com.ilizma.schedule.data.model

sealed class ScheduleState {

    data class Success(
        val programList: List<Program>,
    ) : ScheduleState()

    data class Error(
        val message: String,
    ) : ScheduleState()

}