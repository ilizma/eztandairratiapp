package com.ilizma.schedule.view.router

import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.view.navigation.Navigator
import kotlinx.coroutines.CoroutineScope

interface ScheduleDetailRouter {

    fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleDetailScreenViewModel,
        navController: Navigator,
    )

}
