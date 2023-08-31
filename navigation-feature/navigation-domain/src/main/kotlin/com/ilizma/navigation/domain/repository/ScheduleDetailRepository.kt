package com.ilizma.navigation.domain.repository

import com.ilizma.navigation.domain.model.ScheduleDetailArgs

interface ScheduleDetailRepository {

    fun saveScheduleDetailArgs(args: ScheduleDetailArgs)
}
