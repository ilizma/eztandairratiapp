package com.ilizma.schedule.flow.navigator

import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

interface ScheduleBackNavigator {

    fun back(navigator: TabNavigator, tab: Tab)

}