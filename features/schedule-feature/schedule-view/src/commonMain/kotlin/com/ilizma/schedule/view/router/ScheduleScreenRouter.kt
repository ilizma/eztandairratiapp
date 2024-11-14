package com.ilizma.schedule.view.router

import androidx.navigation.NavHostController
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface ScheduleScreenRouter {

    fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleScreenViewModel,
        navController: NavHostController,
        bottomNavController: NavHostController,
    )

}