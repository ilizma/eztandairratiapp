package com.ilizma.schedule.data.datasource

import com.ilizma.schedule.data.model.ScheduleState
import io.reactivex.rxjava3.core.Single

interface ScheduleDataSource {

    fun get() : Single<ScheduleState>

}