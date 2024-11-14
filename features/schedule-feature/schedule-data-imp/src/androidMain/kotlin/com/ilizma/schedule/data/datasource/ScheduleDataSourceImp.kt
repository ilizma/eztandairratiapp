package com.ilizma.schedule.data.datasource

import com.ilizma.api.data.EztandaApi
import com.ilizma.resources.Res
import com.ilizma.resources.unknown_error
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import com.ilizma.schedule.data.model.ScheduleState
import org.jetbrains.compose.resources.getString
import kotlin.let

class ScheduleDataSourceImp(
    private val api: EztandaApi,
    private val mapper: ScheduleStateDTOMapper,
) : ScheduleDataSource {

    override suspend fun get(): ScheduleState = try {
        api.getSchedule()
            .let { mapper.toData(it, getString(Res.string.unknown_error)) }
    } catch (e: Exception) {
        ScheduleState.Error(e.message ?: getString(Res.string.unknown_error))
    }

}