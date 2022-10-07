package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Days
import com.ilizma.schedule.presentation.model.Days as PresentationDays

class DaysMapper(
    private val mapper: DayListMapper,
) {

    fun toPresentation(
        days: Days,
    ): PresentationDays = days.dayList
        .let { mapper.toPresentation(it) }
        .let { PresentationDays(it) }

}