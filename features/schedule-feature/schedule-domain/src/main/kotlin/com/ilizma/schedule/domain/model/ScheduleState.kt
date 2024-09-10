package com.ilizma.schedule.domain.model

sealed class ScheduleState {

    data class Success(
        val programList: List<Program>
    ) : ScheduleState()

    data class Error(val message: String) : ScheduleState()

}