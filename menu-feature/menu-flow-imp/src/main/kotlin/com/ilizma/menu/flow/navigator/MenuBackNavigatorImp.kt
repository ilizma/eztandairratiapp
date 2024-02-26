package com.ilizma.menu.flow.navigator

import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

class MenuBackNavigatorImp : MenuBackNavigator {

    override fun back(
        navigator: TabNavigator,
        tab: Tab,
    ) {
        navigator.current = tab
    }

}