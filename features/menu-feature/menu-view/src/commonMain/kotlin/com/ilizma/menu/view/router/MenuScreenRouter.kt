package com.ilizma.menu.view.router

import androidx.navigation.NavHostController
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import kotlinx.coroutines.CoroutineScope

interface MenuScreenRouter {

    fun init(
        coroutineScope: CoroutineScope,
        viewModel: MenuScreenViewModel,
        navController: NavHostController,
    )

}