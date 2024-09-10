package com.ilizma.schedule.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

class ScheduleBackNavigatorImp : ScheduleBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack()
        }
    }

}