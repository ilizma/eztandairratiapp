package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilizma.schedule.presentation.model.Schedule
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction

abstract class ScheduleDetailViewModel : ViewModel() {

    abstract val dayName: LiveData<String>

    abstract val schedule: LiveData<Schedule>

    abstract val error: LiveData<String>

    abstract val navigationAction: LiveData<ScheduleDetailNavigationAction>

    abstract fun getSchedule()

    abstract fun onBack()

}