package com.ilizma.menu.flow.navigator

import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

interface MenuBackNavigator {

    fun back(navigator: TabNavigator, tab: Tab)

}