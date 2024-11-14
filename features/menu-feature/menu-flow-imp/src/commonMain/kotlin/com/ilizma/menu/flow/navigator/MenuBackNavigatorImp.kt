package com.ilizma.menu.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.ilizma.player.flow.model.RadioTab

class MenuBackNavigatorImp : MenuBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        if (navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
            navController.popBackStack(
                route = RadioTab,
                inclusive = false,
            )
        }
    }

}