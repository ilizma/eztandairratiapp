package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleDetailArgs

interface ScheduleDetailArgsCache {

    fun get(): ScheduleDetailArgs?

    fun set(args: ScheduleDetailArgs)

}