package com.ilizma.schedule.flow.navigator

import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.view.navigation.Navigator

class ScheduleDetailNavigator {

    fun navigate(
        navController: Navigator,
        id: Int,
        name: String,
    ) {
        ScheduleDetail(
            id = id,
            name = name,
        ).let { navController.navigate(it) }
    }

}
