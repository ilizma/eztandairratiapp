package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleDetailArgs

class ScheduleDetailArgsCache {

    private var cache: ScheduleDetailArgs? = null

    fun get(): ScheduleDetailArgs? = cache

    fun set(args: ScheduleDetailArgs) {
        cache = args
    }

}