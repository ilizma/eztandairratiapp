package com.ilizma.schedule.flow.router

import androidx.lifecycle.coroutineScope
import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleBackNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ScheduleScreenRouterImp(
    private val scheduleBackNavigator: ScheduleBackNavigator,
    private val scheduleDetailNavigator: ScheduleDetailNavigator,
) : ScheduleScreenRouter {

    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: ScheduleScreenViewModel,
        navController: NavHostController,
        bottomNavController: NavHostController,
    ) {
        coroutineScope.launch {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    navController = navController,
                    bottomNavController = bottomNavController,
                    action = it
                )
            }
        }
    }

    private fun onNavigationAction(
        navController: NavHostController,
        bottomNavController: NavHostController,
        action: ScheduleScreenNavigationAction,
    ) {
        when (action) {
            Back -> scheduleBackNavigator.back(
                navController = bottomNavController,
            )

            is ScheduleScreenNavigationAction.ScheduleDetail -> action.day
                .let {
                    scheduleDetailNavigator.navigate(
                        navController = navController,
                        id = it.id,
                        name = it.name
                    )
                }
        }
    }

}