package com.ilizma.schedule.presentation.model

sealed class ScheduleScreenNavigationAction {

    object Back : ScheduleScreenNavigationAction()

    data class ScheduleDetail(val day: Day) : ScheduleScreenNavigationAction()

}