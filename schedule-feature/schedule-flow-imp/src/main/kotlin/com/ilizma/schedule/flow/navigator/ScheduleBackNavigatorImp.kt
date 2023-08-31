package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.navigation.view.model.NavigationBarItemType

class ScheduleBackNavigatorImp : ScheduleBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack(
            route = NavigationBarItemType.RADIO.name,
            inclusive = false,
        )
    }

}