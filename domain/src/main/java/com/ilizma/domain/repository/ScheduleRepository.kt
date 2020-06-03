package com.ilizma.domain.repository

import com.ilizma.domain.entity.schedule.Data
import io.reactivex.Completable
import io.reactivex.Single

interface ScheduleRepository {

    fun getSchedule(): Completable

    fun getScheduleFromLocal(day: Int): Single<List<Data.Schedule>>

    fun isScheduleFromLocal(): Completable

    fun nuke(): Completable

}