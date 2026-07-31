package com.ilizma.menu.flow.navigator

import com.ilizma.view.navigation.Navigator

class MenuBackNavigatorImp: MenuBackNavigator {

    override fun back(
        navigator: Navigator,
    ) {
        navigator.goBack()
    }

}
