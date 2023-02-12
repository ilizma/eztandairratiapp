package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.cache.ScheduleDataSourceCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.mapper.ScheduleStateMapper
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository
import io.reactivex.rxjava3.core.Single
import com.ilizma.schedule.data.model.ScheduleState as DataScheduleState

class ScheduleRepositoryImp(
    private val dataSource: ScheduleDataSource,
    private val dayIdDataSource: DayIdDataSource,
    private val cache: ScheduleDataSourceCache,
    private val mapper: ScheduleStateMapper,
) : ScheduleRepository {

    override fun get(): Single<ScheduleState> = (getFromCacheIfExist() ?: getFromRemoteAndSaveCache())
        .map { mapper.toDomain(it, dayIdDataSource.get()) }

    private fun getFromCacheIfExist(
    ): Single<DataScheduleState>? = cache.get()
        ?.let { Single.just(it) }

    private fun getFromRemoteAndSaveCache(
    ): Single<DataScheduleState> = dataSource.get()
        .doOnSuccess { cache.set(it) }

}