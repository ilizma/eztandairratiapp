package com.ilizma.schedule.flow.router

import androidx.lifecycle.LifecycleCoroutineScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
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
        navigator: Navigator,
        tabNavigator: TabNavigator,
        tab: Tab,
    ) {
        lifecycleCoroutineScope.launch {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    navigator = navigator,
                    tabNavigator = tabNavigator,
                    tab = tab,
                    action = it
                )
            }
        }
    }

    private fun onNavigationAction(
        navigator: Navigator,
        tabNavigator: TabNavigator,
        tab: Tab,
        action: ScheduleScreenNavigationAction,
    ) {
        when (action) {
            Back -> scheduleBackNavigator.back(
                navigator = tabNavigator,
                tab = tab,
            )

            is ScheduleScreenNavigationAction.ScheduleDetail -> action.day
                .let {
                    scheduleDetailNavigator.navigate(
                        navigator = navigator,
                        id = it.id,
                        name = it.name
                    )
                }
        }
    }

}