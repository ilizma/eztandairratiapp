package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.player.flow.model.RadioTab

class ScheduleBackNavigatorImp : ScheduleBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack(
            route = RadioTab,
            inclusive = false,
        )
    }

}