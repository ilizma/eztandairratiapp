package com.ilizma.schedule.data.datasource

import com.ilizma.api.data.EztandaApi
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import com.ilizma.schedule.data.model.ScheduleState
import io.reactivex.rxjava3.core.Single

class ScheduleDataSourceImp(
    private val api: EztandaApi,
    private val mapper: ScheduleStateDTOMapper,
    private val unknownErrorMessage: String, // TODO: 24/12/21 R.string.unknown_error
) : ScheduleDataSource {

    override fun get(): Single<ScheduleState> = api.getSchedule()
        .map { mapper.toData(it, unknownErrorMessage) }

}