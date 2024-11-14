package com.ilizma.schedule.data.datasource

import com.ilizma.schedule.data.model.ScheduleState

interface ScheduleDataSource {

    suspend fun get() : ScheduleState

}