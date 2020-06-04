package com.ilizma.data.repository.schedule

import com.ilizma.data.repository.schedule.datasources.ScheduleLocalDataSource
import com.ilizma.data.repository.schedule.datasources.ScheduleRemoteDataSource
import com.ilizma.domain.entity.schedule.Schedule
import com.ilizma.domain.repository.ScheduleRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource,
    private val scheduleLocalDataSource: ScheduleLocalDataSource
) : ScheduleRepository {

    override fun getSchedule(): Completable =
        scheduleRemoteDataSource.getSchedule()
            .doOnSuccess { scheduleList ->
                scheduleLocalDataSource.saveSchedule(scheduleList)
            }
            .ignoreElement()

    override fun getScheduleFromLocal(day: Int): Single<List<Schedule>> =
        scheduleLocalDataSource.getSchedule().map { allScheduleList ->
            allScheduleList.filter { it.day == day.toString() }
        }

    override fun isScheduleFromLocal(): Completable =
        scheduleLocalDataSource.getSchedule()
            .ignoreElement()

    override fun nuke(): Completable =
        scheduleLocalDataSource.nuke()

}