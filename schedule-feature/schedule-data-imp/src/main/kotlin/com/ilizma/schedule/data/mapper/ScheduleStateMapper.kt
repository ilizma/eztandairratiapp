package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.data.model.ScheduleState as DataScheduleState

class ScheduleStateMapper(
    private val mapper: ProgramListMapper,
) {

    fun toDomain(
        state: DataScheduleState,
        dayId: Int,
    ): ScheduleState = when (state) {
        is DataScheduleState.Error -> ScheduleState.Error(state.message)
        is DataScheduleState.Success -> state.programList
            .let { mapper.toDomain(it, dayId) }
            .let { ScheduleState.Success(it) }
    }

}