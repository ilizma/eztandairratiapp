package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleState
import kotlinx.coroutines.flow.Flow

abstract class ScheduleScreenDetailViewModel : ViewModel() {

    abstract val dayName: Flow<String>

    abstract val scheduleState: Flow<ScheduleState>

    abstract val navigationAction: LiveData<ScheduleDetailNavigationAction>

    abstract fun getTitle()

    abstract fun getSchedule()

    abstract fun retrySchedule()

    abstract fun onBack()

}