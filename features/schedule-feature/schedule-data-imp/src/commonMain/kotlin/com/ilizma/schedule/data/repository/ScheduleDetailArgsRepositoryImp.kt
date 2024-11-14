package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.model.ScheduleDetailArgs
import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository

class ScheduleDetailArgsRepositoryImp(
    private val cache: ScheduleDetailArgsCache,
) : ScheduleDetailArgsRepository {

    override fun save(
        id: Int,
        name: String
    ) {
        ScheduleDetailArgs(
            id = id,
            name = name
        ).let { cache.set(it) }
    }

}