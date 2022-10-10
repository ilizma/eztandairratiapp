package com.ilizma.menu.flow.navigator

import androidx.navigation.NavController

class MenuBackCloseNavigatorImp(
    private val navController: NavController,
) : MenuBackCloseNavigator {

    override fun back() {
        navController.popBackStack()
    }

}