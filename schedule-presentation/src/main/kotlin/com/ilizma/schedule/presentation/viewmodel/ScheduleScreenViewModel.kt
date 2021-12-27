package com.ilizma.schedule.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction

abstract class ScheduleScreenViewModel : ViewModel() {

    abstract val days: LiveData<Days>

    abstract val navigationAction: LiveData<ScheduleScreenNavigationAction>

    abstract fun onClick(day: Day)

    abstract fun onBack()

}