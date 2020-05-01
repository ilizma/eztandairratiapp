package com.ilizma.data.repository.schedule.datasources

import com.ilizma.data.datasources.remote.BaseRemoteDataSource
import com.ilizma.data.repository.schedule.data.ScheduleApi
import com.ilizma.domain.entity.schedule.Data
import io.reactivex.Single
import javax.inject.Inject

class ScheduleRemoteDataSource @Inject constructor(
    private val scheduleApi: ScheduleApi
) : BaseRemoteDataSource() {

    fun getSchedule(): Single<Data> {
        return modifySingle(scheduleApi.getSchedule())
    }

}