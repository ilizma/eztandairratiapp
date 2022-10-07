package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Day
import com.ilizma.schedule.presentation.model.Day as PresentationDay

class DayMapper {

    fun toPresentation(
        day: Day,
    ): PresentationDay = PresentationDay(
        id = day.id,
        name = day.name
    )

}