package com.ilizma.schedule.presentation.model

sealed interface ScheduleDetailScreenIntent {
    data class SaveCache(
        val id: Int,
        val name: String,
    ) : ScheduleDetailScreenIntent

    object GetSchedule : ScheduleDetailScreenIntent
    object RetrySchedule : ScheduleDetailScreenIntent
    object Back : ScheduleDetailScreenIntent
}
