package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Days
import com.ilizma.schedule.data.model.Days as DataDays

class DaysMapper(
    private val mapper: DayListMapper,
) {

    fun toDomain(
        days: DataDays,
    ): Days = days.dayList
        .let { mapper.toDomain(it) }
        .let { Days(it) }

}