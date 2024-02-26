package com.ilizma.schedule.flow.navigator

import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

class ScheduleBackNavigatorImp : ScheduleBackNavigator {

    override fun back(
        navigator: TabNavigator,
        tab: Tab,
    ) {
        navigator.current = tab
    }

}