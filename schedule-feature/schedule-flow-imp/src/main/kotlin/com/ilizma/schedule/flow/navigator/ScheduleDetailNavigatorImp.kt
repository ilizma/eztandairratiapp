package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.navigation.view.model.Routes.ScheduleDetail

class ScheduleDetailNavigatorImp : ScheduleDetailNavigator {

    override fun navigate(
        navController: NavHostController,
        id: Int,
        name: String,
    ) {
        ScheduleDetail.createRoute(
            id = id,
            name = name,
         ).let { navController.navigate(it) }
    }

}