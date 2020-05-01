package com.ilizma.data.repository.schedule

import com.ilizma.data.repository.schedule.datasources.ScheduleLocalDataSource
import com.ilizma.data.repository.schedule.datasources.ScheduleRemoteDataSource
import com.ilizma.domain.repository.ScheduleRepository
import io.reactivex.Completable
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource,
    private val scheduleLocalDataSource: ScheduleLocalDataSource
) : ScheduleRepository {

    override fun getSchedule(): Completable =
        scheduleRemoteDataSource.getSchedule()
            .doOnSuccess { data ->
                scheduleLocalDataSource.saveSchedule(data.schedule)
            }
            .ignoreElement()

    override fun nuke(): Completable =
        scheduleLocalDataSource.nuke()

}