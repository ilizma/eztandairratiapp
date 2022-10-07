package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Day
import com.ilizma.schedule.presentation.model.Day as PresentationDay

class DayListMapper(
    private val mapper: DayMapper,
) {

    fun toPresentation(
        dayList: List<Day>,
    ): List<PresentationDay> = dayList
        .map { mapper.toPresentation(it) }

}