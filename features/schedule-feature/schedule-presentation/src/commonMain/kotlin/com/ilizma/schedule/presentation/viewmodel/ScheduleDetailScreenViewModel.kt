package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import com.ilizma.schedule.presentation.model.ScheduleState
import kotlinx.coroutines.flow.Flow

abstract class ScheduleDetailScreenViewModel : ViewModel() {

    abstract val scheduleState: Flow<ScheduleState>

    abstract val navigationAction: Flow<ScheduleDetailNavigationAction>

    abstract fun onIntent(intent: ScheduleDetailScreenIntent)

}