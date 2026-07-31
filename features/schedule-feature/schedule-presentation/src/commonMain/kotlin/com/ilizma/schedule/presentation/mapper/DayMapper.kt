package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.presentation.model.Day

class DayMapper {

    fun from(
        index: Int,
        day: String,
    ): Day = Day(
        id = index + 1,
        name = day
    )

}