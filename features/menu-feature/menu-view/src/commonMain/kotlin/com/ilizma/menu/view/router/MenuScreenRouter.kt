package com.ilizma.menu.view.router

import androidx.compose.ui.platform.UriHandler
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.view.navigation.Navigator
import kotlinx.coroutines.CoroutineScope

interface MenuScreenRouter {

    fun init(
        uriHandler: UriHandler,
        coroutineScope: CoroutineScope,
        viewModel: MenuScreenViewModel,
        navigator: Navigator,
    )

}
