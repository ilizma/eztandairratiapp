package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import kotlinx.coroutines.flow.Flow

abstract class ScheduleScreenViewModel : ViewModel() {

    abstract val days: Flow<Days>

    abstract val navigationAction: Flow<ScheduleScreenNavigationAction>

    abstract fun onClick(day: Day)

    abstract fun onBack()

}