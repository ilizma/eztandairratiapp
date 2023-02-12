package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ScheduleDTO
import com.ilizma.schedule.data.model.ScheduleState

class ScheduleStateDTOMapper(
    private val mapper: ProgramDTOListMapper,
) {

    fun toData(
        result: ScheduleDTO,
        unknownErrorMessage: String,
    ): ScheduleState = result.schedule
        ?.let { mapper.toData(it) }
        ?.let { ScheduleState.Success(it) }
        ?: ScheduleState.Error(unknownErrorMessage)

}