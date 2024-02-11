package com.ilizma.schedule.flow.router

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleBackNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import kotlinx.coroutines.launch

class ScheduleScreenRouterImp(
    private val lifecycleCoroutineScope: LifecycleCoroutineScope,
    viewModelLazy: Lazy<ScheduleScreenViewModel>,
    private val scheduleBackNavigator: ScheduleBackNavigator,
    private val scheduleDetailNavigator: ScheduleDetailNavigator,
) : ScheduleScreenRouter {

    private val viewModel: ScheduleScreenViewModel by viewModelLazy

    override fun init(
        navController: NavHostController,
        mainNavController: NavHostController,
    ) {
        lifecycleCoroutineScope.launch {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    navController = navController,
                    mainNavController = mainNavController,
                    action = it
                )
            }
        }
    }

    private fun onNavigationAction(
        navController: NavHostController,
        mainNavController: NavHostController,
        action: ScheduleScreenNavigationAction,
    ) {
        when (action) {
            Back -> scheduleBackNavigator.back(mainNavController)
            is ScheduleScreenNavigationAction.ScheduleDetail -> action.day
                .let { scheduleDetailNavigator.navigate(navController, it.id, it.name) }
        }
    }

}