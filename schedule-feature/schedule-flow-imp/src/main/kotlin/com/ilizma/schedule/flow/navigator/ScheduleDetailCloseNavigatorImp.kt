package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController

class ScheduleDetailCloseNavigatorImp : ScheduleDetailCloseNavigator {

    override fun close(
        navController: NavHostController,
    ) {
        navController.popBackStack()
    }

}