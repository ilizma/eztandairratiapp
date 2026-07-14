package com.ilizma.schedule.view.router

import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.view.navigation.Navigator
import kotlinx.coroutines.CoroutineScope

interface ScheduleScreenRouter {

    fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleScreenViewModel,
        navController: Navigator,
        bottomNavController: Navigator,
    )

}
