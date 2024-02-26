package com.ilizma.schedule.view.router

import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

interface ScheduleScreenRouter {

    fun init(
        navigator: Navigator,
        tabNavigator: TabNavigator,
        tab: Tab,
    )

}