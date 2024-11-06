package com.ilizma.schedule.flow.router

import androidx.lifecycle.coroutineScope
import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import kotlinx.coroutines.launch

class ScheduleDetailRouterImp(
    private val closeNavigator: ScheduleDetailCloseNavigator,
) : ScheduleDetailRouter {

    override fun init(
        viewModel: ScheduleDetailScreenViewModel,
        navController: NavHostController,
    ) {
        navController.currentBackStackEntry?.lifecycle?.coroutineScope?.launch {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    navController = navController,
                    action = it,
                )
            }
        }
    }

    private fun onNavigationAction(
        navController: NavHostController,
        action: ScheduleDetailNavigationAction,
    ) {
        when (action) {
            Back -> closeNavigator.close(navController)
        }
    }

}