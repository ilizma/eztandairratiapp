package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.presentation.model.ScheduleState

class ProgramListMapper(
    private val mapper: ProgramTypeMapper,
) {

    fun toPresentation(
        dayList: List<Program>,
    ): ScheduleState = dayList
        .map { mapper.from(it) }
        .let { ScheduleState.Success(it) }

}