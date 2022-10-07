package com.ilizma.schedule.domain.repository

import com.ilizma.schedule.domain.model.ScheduleState
import io.reactivex.rxjava3.core.Single

interface ScheduleRepository {

    fun get() : Single<ScheduleState>

}