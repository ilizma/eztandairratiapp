package com.ilizma.schedule.view.router

import androidx.navigation.NavHostController
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface ScheduleDetailRouter {

    fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleDetailScreenViewModel,
        navController: NavHostController,
    )

}