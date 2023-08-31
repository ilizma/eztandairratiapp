package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.datasource.DaysDataSource
import com.ilizma.schedule.data.mapper.DaysMapper
import com.ilizma.schedule.domain.model.Days
import com.ilizma.schedule.domain.repository.DaysRepository

class DaysRepositoryImp(
    private val daysDataSource: DaysDataSource,
    private val mapper: DaysMapper,
) : DaysRepository {

    override fun get(): Days = daysDataSource.get()
        .let { mapper.toDomain(it) }

}