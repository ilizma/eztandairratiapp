package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState

class ScheduleStateMapper(
    private val mapper: ProgramTypeMapper,
) {

    fun from(
        state: ScheduleState
    ): PresentationScheduleState = when (state) {
        is ScheduleState.Error -> PresentationScheduleState.Error(state.message)
        is ScheduleState.Success -> from(state.programList)
    }

    fun from(
        dayList: List<Program>,
    ): PresentationScheduleState = dayList
        .map { mapper.from(it) }
        .let { PresentationScheduleState.Success(it) }

}