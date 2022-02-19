package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavController

class ScheduleDetailBackCloseNavigatorImp(
    private val navController: NavController,
) : ScheduleDetailBackCloseNavigator {

    override fun close() {
        navController.popBackStack()
    }

}