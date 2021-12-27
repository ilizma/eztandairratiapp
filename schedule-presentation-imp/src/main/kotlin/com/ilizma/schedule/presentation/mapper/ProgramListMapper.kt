package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.presentation.model.Schedule

class ProgramListMapper(
    private val mapper: ProgramMapper,
) {

    fun toPresentation(
        dayList: List<Program>,
    ): Schedule = dayList
        .map { mapper.toPresentation(it) }
        .let { Schedule(it) }

}