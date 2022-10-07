package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ScheduleDTO
import com.ilizma.schedule.data.model.ScheduleState
import retrofit2.adapter.rxjava3.Result

class ScheduleStateDTOMapper(
    private val mapper: ProgramDTOListMapper,
) {

    fun toData(
        result: Result<ScheduleDTO>,
        unknownErrorMessage: String,
    ): ScheduleState = when {
        result.isError -> ScheduleState.Error(result.error()?.message ?: "")
        else -> result.response()?.body()?.schedule
            ?.let { mapper.toData(it) }
            ?.let { ScheduleState.Success(it) }
            ?: ScheduleState.Error(unknownErrorMessage)
    }

}