package com.ilizma.schedule.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

class ScheduleDetailCloseNavigatorImp : ScheduleDetailCloseNavigator {

    override fun close(
        navController: NavHostController,
    ) {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack()
        }
    }

}