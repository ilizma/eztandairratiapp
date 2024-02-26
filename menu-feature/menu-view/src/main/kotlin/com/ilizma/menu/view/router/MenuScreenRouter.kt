package com.ilizma.menu.view.router

import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

interface MenuScreenRouter {

    fun init(navigator: TabNavigator, tab: Tab)

}