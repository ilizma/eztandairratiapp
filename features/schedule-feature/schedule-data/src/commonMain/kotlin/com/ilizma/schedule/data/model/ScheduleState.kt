package com.ilizma.schedule.data.model

sealed interface ScheduleState {

    data class Success(
        val programList: List<Program>,
    ) : ScheduleState

    data class Error(
        val message: String,
    ) : ScheduleState

}