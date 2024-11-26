package com.ilizma.menu.view.router

import androidx.compose.ui.platform.UriHandler
import androidx.navigation.NavHostController
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface MenuScreenRouter {

    fun init(
        uriHandler: UriHandler,
        coroutineScope: CoroutineScope,
        viewModel: MenuScreenViewModel,
        navController: NavHostController,
    )

}