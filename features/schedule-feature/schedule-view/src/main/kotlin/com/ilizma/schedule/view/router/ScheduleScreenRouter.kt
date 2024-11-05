package com.ilizma.schedule.view.router

import androidx.navigation.NavHostController
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel

interface ScheduleScreenRouter {

    fun init(
        viewModel: ScheduleScreenViewModel,
        navController: NavHostController,
        bottomNavController: NavHostController,
    )

}