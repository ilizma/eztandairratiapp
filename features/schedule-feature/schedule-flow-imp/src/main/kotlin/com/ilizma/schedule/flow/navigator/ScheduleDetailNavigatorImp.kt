package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.model.ScheduleDetail

class ScheduleDetailNavigatorImp : ScheduleDetailNavigator {

    override fun navigate(
        navController: NavHostController,
        id: Int,
        name: String,
    ) {
        ScheduleDetail(
            id = id,
            name = name,
        ).let { navController.navigate(it) }
    }

}