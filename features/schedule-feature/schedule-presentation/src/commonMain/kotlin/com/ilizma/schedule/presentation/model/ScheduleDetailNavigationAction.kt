package com.ilizma.schedule.presentation.model

sealed interface ScheduleDetailNavigationAction {

    data object Back : ScheduleDetailNavigationAction

}