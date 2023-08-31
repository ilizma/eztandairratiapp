package com.ilizma.menu.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.navigation.view.model.NavigationBarItemType

class MenuBackNavigatorImp : MenuBackNavigator {

    override fun back(
        navController: NavHostController,
    ) {
        navController.popBackStack(
            route = NavigationBarItemType.RADIO.name,
            inclusive = false,
        )
    }

}