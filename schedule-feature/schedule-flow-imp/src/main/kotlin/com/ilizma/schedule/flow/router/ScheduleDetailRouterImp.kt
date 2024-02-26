package com.ilizma.schedule.flow.router

import androidx.lifecycle.LifecycleCoroutineScope
import cafe.adriel.voyager.navigator.Navigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import kotlinx.coroutines.launch

class ScheduleDetailRouterImp(
    private val lifecycleCoroutineScope: LifecycleCoroutineScope,
    viewModelLazy: Lazy<ScheduleScreenDetailViewModel>,
    private val closeNavigator: ScheduleDetailCloseNavigator,
) : ScheduleDetailRouter {

    private val viewModel: ScheduleScreenDetailViewModel by viewModelLazy

    override fun init(
        navigator: Navigator,
    ) {
        lifecycleCoroutineScope.launch {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    navigator = navigator,
                    action = it,
                )
            }
        }
    }

    private fun onNavigationAction(
        navigator: Navigator,
        action: ScheduleDetailNavigationAction,
    ) {
        when (action) {
            Back -> closeNavigator.close(navigator)
        }
    }

}