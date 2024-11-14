package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleDetailArgs

class ScheduleDetailArgsCacheImp : ScheduleDetailArgsCache {

    private var cache: ScheduleDetailArgs? = null

    override fun get(): ScheduleDetailArgs? = cache

    override fun set(args: ScheduleDetailArgs) {
        cache = args
    }

}