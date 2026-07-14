package com.ilizma.schedule.flow.router

import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.view.navigation.Navigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScheduleDetailRouterImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val closeNavigator: ScheduleDetailCloseNavigator,
) : ScheduleDetailRouter {

    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleDetailScreenViewModel,
        navController: Navigator,
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
        navController: Navigator,
        action: ScheduleDetailNavigationAction,
    ) {
        when (action) {
            Back -> closeNavigator.close(navController)
        }
    }

}
