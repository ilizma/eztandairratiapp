package com.ilizma.menu.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.player.flow.model.RadioTab

class MenuBackNavigatorImp : MenuBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack(
            route = RadioTab,
            inclusive = false,
        )
    }

}