package com.ilizma.data.repository.schedule.datasources

import com.ilizma.data.datasources.local.BaseLocalDataSource
import com.ilizma.data.datasources.local.SharedPreferencesAssistant
import com.ilizma.data.datasources.local.SharedPreferencesKeys.SCHEDULE_KEY
import com.ilizma.data.datasources.local.SharedPreferencesKeys.SCHEDULE_PREF
import com.ilizma.domain.entity.schedule.Data
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class ScheduleLocalDataSource @Inject constructor(
    @Named(SCHEDULE_PREF)
    private val assistant: SharedPreferencesAssistant
) : BaseLocalDataSource() {

    fun saveSchedule(schedule: List<Data.Schedule>) =
        assistant.saveString(SCHEDULE_KEY, schedule.toJson())

    fun getSchedule(): Single<List<Data.Schedule>> =
        assistant.getString(SCHEDULE_KEY).fromJsonList()

    fun nuke(): Completable =
        assistant.nuke()

}