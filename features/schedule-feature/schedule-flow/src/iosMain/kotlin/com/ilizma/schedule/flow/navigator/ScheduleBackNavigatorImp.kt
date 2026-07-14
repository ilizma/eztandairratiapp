package com.ilizma.schedule.flow.navigator

import com.ilizma.view.navigation.Navigator

class ScheduleBackNavigatorImp : ScheduleBackNavigator {

    override fun back(
        navController: Navigator,
    ) {
        navController.goBack()
    }

}
