package com.ilizma.data.repository.schedule.datasources

import com.ilizma.data.datasources.local.BaseLocalDataSource
import com.ilizma.data.datasources.local.SharedPreferencesAssistant
import com.ilizma.data.datasources.local.SharedPreferencesKeys.SCHEDULE_KEY
import com.ilizma.data.datasources.local.SharedPreferencesKeys.SCHEDULE_PREF
import com.ilizma.domain.entity.schedule.Schedule
import com.squareup.moshi.Moshi
import dagger.Lazy
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Named

class ScheduleLocalDataSource @Inject constructor(
    @Named(SCHEDULE_PREF)
    private val assistant: SharedPreferencesAssistant,
) : BaseLocalDataSource() {

    @Inject
    override lateinit var moshi: Lazy<Moshi>

    fun saveSchedule(
        scheduleList: List<Schedule>,
    ) {
        assistant.saveString(SCHEDULE_KEY, scheduleList.toJson())
    }

    fun getSchedule(
    ): Single<List<Schedule>> = assistant.getString(SCHEDULE_KEY).fromJsonList()

    fun nuke(
    ): Completable = assistant.nuke()

}