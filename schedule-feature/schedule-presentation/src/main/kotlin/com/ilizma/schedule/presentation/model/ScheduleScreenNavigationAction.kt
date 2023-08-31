package com.ilizma.schedule.presentation.model

sealed class ScheduleScreenNavigationAction {

    data object Back : ScheduleScreenNavigationAction()

    data class ScheduleDetail(val day: Day) : ScheduleScreenNavigationAction()

}