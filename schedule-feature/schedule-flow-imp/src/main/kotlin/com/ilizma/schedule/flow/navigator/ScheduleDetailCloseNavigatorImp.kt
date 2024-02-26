package com.ilizma.schedule.flow.navigator

import cafe.adriel.voyager.navigator.Navigator

class ScheduleDetailCloseNavigatorImp : ScheduleDetailCloseNavigator {

    override fun close(
        navigator: Navigator,
    ) {
        navigator.pop()
    }

}