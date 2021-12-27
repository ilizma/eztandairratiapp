package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.domain.repository.DayNameRepository

class DayNameRepositoryImp(
    private val dataSource: DayNameDataSource,
) : DayNameRepository {

    override fun get(): String = dataSource.get()


}