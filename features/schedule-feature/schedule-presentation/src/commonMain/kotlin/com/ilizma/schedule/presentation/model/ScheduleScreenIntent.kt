package com.ilizma.schedule.presentation.model

sealed interface ScheduleScreenIntent {

    data class Click(
        val day: Day,
    ) : ScheduleScreenIntent

    object Back : ScheduleScreenIntent

}
