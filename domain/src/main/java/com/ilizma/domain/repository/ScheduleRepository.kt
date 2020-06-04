package com.ilizma.domain.repository

import com.ilizma.domain.entity.schedule.Schedule
import io.reactivex.Completable
import io.reactivex.Single

interface ScheduleRepository {

    fun getSchedule(): Completable

    fun getScheduleFromLocal(day: Int): Single<List<Schedule>>

    fun isScheduleFromLocal(): Completable

    fun nuke(): Completable

}