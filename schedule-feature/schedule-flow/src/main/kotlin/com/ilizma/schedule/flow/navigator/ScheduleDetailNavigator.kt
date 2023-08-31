package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController

interface ScheduleDetailNavigator {

    fun navigate(navController: NavHostController, id: Int, name: String)

}