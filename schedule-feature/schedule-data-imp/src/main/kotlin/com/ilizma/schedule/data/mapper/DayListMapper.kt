package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Day
import com.ilizma.schedule.data.model.Day as DataDay

class DayListMapper(
    private val mapper: DayMapper,
) {

    fun toDomain(
        dayList: List<DataDay>,
    ): List<Day> = dayList
        .map { mapper.toDomain(it) }

}