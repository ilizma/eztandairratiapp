package com.ilizma.schedule.data.datasource

import com.ilizma.schedule.data.mapper.DaysArrayMapper
import com.ilizma.schedule.data.model.Days

class DaysDataSourceImp(
    private val daysArray: Array<String>,
    private val mapper: DaysArrayMapper,
) : DaysDataSource {

    override fun get(): Days = daysArray
        .let { mapper.toData(it) }

}