package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.cache.ScheduleStateCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.mapper.ScheduleStateMapper
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository
import com.ilizma.schedule.data.model.ScheduleState as DataScheduleState

class ScheduleRepositoryImp(
    private val dataSource: ScheduleDataSource,
    private val dayIdDataSource: DayIdDataSource,
    private val cache: ScheduleStateCache,
    private val mapper: ScheduleStateMapper,
) : ScheduleRepository {

    override suspend fun get(): ScheduleState = (cache.get() ?: getFromRemoteAndSaveCache())
        .let { mapper.from(it, dayIdDataSource.get()) }

    private suspend fun getFromRemoteAndSaveCache(
    ): DataScheduleState = dataSource.get()
        .also { cache.set(it) }

}