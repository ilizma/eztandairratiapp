package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleState

class ScheduleDataSourceCacheImp : ScheduleDataSourceCache {

    private var cache: ScheduleState? = null

    override fun get(): ScheduleState? = cache

    override fun set(state: ScheduleState) {
        cache = state
    }

}