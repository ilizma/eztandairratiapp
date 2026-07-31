package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleState

class ScheduleStateCache {

    private var cache: ScheduleState? = null

    fun get(): ScheduleState? = cache

    fun set(state: ScheduleState) {
        cache = state
    }

}