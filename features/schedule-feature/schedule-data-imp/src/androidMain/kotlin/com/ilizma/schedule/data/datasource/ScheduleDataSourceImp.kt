package com.ilizma.schedule.data.datasource

import com.ilizma.api.data.EztandaApi
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import com.ilizma.schedule.data.model.ScheduleState
import kotlin.let

class ScheduleDataSourceImp(
    private val api: EztandaApi,
    private val mapper: ScheduleStateDTOMapper,
    private val unknownError: String,
) : ScheduleDataSource {

    override suspend fun get(): ScheduleState = try {
        api.getSchedule()
            .let { mapper.from(it, unknownError) }
    } catch (e: Exception) {
        ScheduleState.Error(e.message ?: unknownError)
    }

}