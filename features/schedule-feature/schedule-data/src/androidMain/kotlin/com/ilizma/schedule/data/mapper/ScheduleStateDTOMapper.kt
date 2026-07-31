package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ScheduleDTO
import com.ilizma.schedule.data.model.ScheduleState
import kotlin.let

class ScheduleStateDTOMapper(
    private val mapper: ProgramDTOListMapper,
) {

    fun from(
        result: ScheduleDTO,
        unknownErrorMessage: String,
    ): ScheduleState = result.schedule
        ?.let { mapper.from(it) }
        ?.let { ScheduleState.Success(it) }
        ?: ScheduleState.Error(unknownErrorMessage)

}