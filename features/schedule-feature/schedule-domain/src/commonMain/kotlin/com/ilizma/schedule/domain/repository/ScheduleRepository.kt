package com.ilizma.schedule.domain.repository

import com.ilizma.schedule.domain.model.ScheduleState

interface ScheduleRepository {

    suspend fun get() : ScheduleState

}