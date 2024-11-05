package com.ilizma.menu.view.router

import androidx.navigation.NavHostController
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel

interface MenuScreenRouter {

    fun init(viewModel: MenuScreenViewModel, navController: NavHostController)

}