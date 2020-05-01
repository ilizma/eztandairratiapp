package com.ilizma.domain.repository

import io.reactivex.Completable

interface ScheduleRepository {

    fun getSchedule(): Completable

    fun nuke(): Completable

}