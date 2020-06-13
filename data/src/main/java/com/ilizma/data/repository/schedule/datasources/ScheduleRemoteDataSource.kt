package com.ilizma.data.repository.schedule.datasources

import android.content.res.Resources
import com.ilizma.data.datasources.remote.BaseRemoteDataSource
import com.ilizma.data.repository.schedule.data.ScheduleApi
import com.ilizma.domain.entity.schedule.Schedule
import com.squareup.moshi.Moshi
import dagger.Lazy
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ScheduleRemoteDataSource @Inject constructor(
    private val scheduleApi: ScheduleApi
) : BaseRemoteDataSource() {

    @Inject
    override lateinit var resources: Lazy<Resources>

    @Inject
    override lateinit var moshi: Lazy<Moshi>

    fun getSchedule(): Single<List<Schedule>> {
        return modifySingle(scheduleApi.getSchedule())
    }

}