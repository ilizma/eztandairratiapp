package com.ilizma.schedule.view.router

import androidx.navigation.NavHostController
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel

interface ScheduleDetailRouter {

    fun init(viewModel: ScheduleDetailScreenViewModel, navController: NavHostController)

}