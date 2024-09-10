package com.ilizma.menu.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

class MenuBackNavigatorImp : MenuBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack()
        }
    }

}