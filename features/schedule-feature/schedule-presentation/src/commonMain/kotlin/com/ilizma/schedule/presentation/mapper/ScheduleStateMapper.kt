package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState

class ScheduleStateMapper(
    private val mapper: ProgramTypeMapper,
) {

    fun from(
        title: String,
        state: ScheduleState
    ): PresentationScheduleState = when (state) {
        is ScheduleState.Error -> PresentationScheduleState.Error(state.message)
        is ScheduleState.Success -> from(
            title = title,
            programList = state.programList
        )
    }

    fun from(
        title: String,
        programList: List<Program>,
    ): PresentationScheduleState = programList
        .map { mapper.from(it) }
        .let {
            PresentationScheduleState.Success(
                title = title,
                list = it
            )
        }

}