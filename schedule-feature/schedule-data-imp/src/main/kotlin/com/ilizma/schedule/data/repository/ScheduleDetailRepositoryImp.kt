package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.mapper.ScheduleDetailArgsMapper
import com.ilizma.navigation.domain.repository.ScheduleDetailRepository
import com.ilizma.navigation.domain.model.ScheduleDetailArgs

class ScheduleDetailRepositoryImp(
    private val cache: ScheduleDetailArgsCache,
    private val mapper: ScheduleDetailArgsMapper,
) : ScheduleDetailRepository {

    override fun saveScheduleDetailArgs(args: ScheduleDetailArgs) {
        mapper.from(args)
            .let { cache.set(it) }
    }

}