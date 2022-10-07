package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.data.model.Day
import com.ilizma.schedule.data.model.Days

class DaysArrayMapper(
    private val mapper: DayMapper,
) {

    fun toData(
        daysArray: Array<String>,
    ): Days = daysArray
        .mapIndexed { index, day -> mapper.toData(index, day) }
        .let { Days(it) }

}