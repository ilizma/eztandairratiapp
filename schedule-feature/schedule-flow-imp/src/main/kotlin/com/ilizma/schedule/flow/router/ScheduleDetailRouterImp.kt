package com.ilizma.schedule.flow.router

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter

class ScheduleDetailRouterImp(
    private val lifecycleOwner: () -> LifecycleOwner,
    viewModelLazy: Lazy<ScheduleScreenDetailViewModel>,
    private val navigator: ScheduleDetailCloseNavigator,
) : ScheduleDetailRouter {

    private val viewModel: ScheduleScreenDetailViewModel by viewModelLazy

    override fun init(
        navController: NavHostController,
    ) {
        viewModel.navigationAction.observe(
            lifecycleOwner(),
        ) {
            onNavigationAction(
                navController = navController,
                action = it,
            )
        }
    }

    private fun onNavigationAction(
        navController: NavHostController,
        action: ScheduleDetailNavigationAction,
    ) {
        when (action) {
            Back -> navigator.close(navController)
        }
    }

}