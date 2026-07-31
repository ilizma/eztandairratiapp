package com.ilizma.schedule.flow.navigator

import com.ilizma.view.navigation.Navigator

class ScheduleDetailCloseNavigatorImp : ScheduleDetailCloseNavigator {

    override fun close(
        navController: Navigator,
    ) {
        navController.goBack()
    }

}
