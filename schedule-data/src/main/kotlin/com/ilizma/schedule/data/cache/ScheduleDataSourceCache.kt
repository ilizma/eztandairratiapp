package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleState

interface ScheduleDataSourceCache {

    fun get(): ScheduleState?

    fun set(state: ScheduleState)

}