package com.ilizma.navigation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ilizma.navigation.presentation.model.ScheduleDetailArgs

abstract class NavigationScreenViewModel : ViewModel() {

    abstract fun saveArgs(args: ScheduleDetailArgs)

}