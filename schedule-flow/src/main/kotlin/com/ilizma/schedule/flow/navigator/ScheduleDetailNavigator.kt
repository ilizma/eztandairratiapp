package com.ilizma.schedule.flow.navigator

import com.ilizma.schedule.flow.model.Day

interface ScheduleDetailNavigator {

    fun navigate(day: Day)

}