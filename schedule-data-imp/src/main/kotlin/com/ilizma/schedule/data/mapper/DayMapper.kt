package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Day
import com.ilizma.schedule.data.model.Day as DataDay

class DayMapper {

    fun toData(
        index: Int,
        day: String,
    ): DataDay = DataDay(
        id = index + 1,
        name = day
    )

    fun toDomain(
        day: DataDay,
    ): Day = Day(
        id = day.id,
        name = day.name
    )

}