package com.ilizma.schedule.flow.router

import androidx.lifecycle.coroutineScope
import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class ScheduleDetailRouterImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val closeNavigator: ScheduleDetailCloseNavigator,
) : ScheduleDetailRouter {

    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleDetailScreenViewModel,
        navController: NavHostController,
    ) {
        coroutineScope.launch(dispatcher) {
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