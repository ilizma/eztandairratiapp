package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavController

class ScheduleBackCloseNavigatorImp(
    private val navController: NavController,
) : ScheduleBackCloseNavigator {

    override fun back() {
        navController.popBackStack()
    }

}