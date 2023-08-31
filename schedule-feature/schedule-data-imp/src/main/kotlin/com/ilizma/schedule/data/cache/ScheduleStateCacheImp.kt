package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleState

class ScheduleStateCacheImp : ScheduleStateCache {

    private var cache: ScheduleState? = null

    override fun get(): ScheduleState? = cache

    override fun set(state: ScheduleState) {
        cache = state
    }

}