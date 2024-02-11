package com.ilizma.schedule.flow.router

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import kotlinx.coroutines.launch

class ScheduleDetailRouterImp(
    private val lifecycleCoroutineScope: LifecycleCoroutineScope,
    viewModelLazy: Lazy<ScheduleScreenDetailViewModel>,
    private val navigator: ScheduleDetailCloseNavigator,
) : ScheduleDetailRouter {

    private val viewModel: ScheduleScreenDetailViewModel by viewModelLazy

    override fun init(
        navController: NavHostController,
    ) {
        lifecycleCoroutineScope.launch {
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
            Back -> navigator.close(navController)
        }
    }

}