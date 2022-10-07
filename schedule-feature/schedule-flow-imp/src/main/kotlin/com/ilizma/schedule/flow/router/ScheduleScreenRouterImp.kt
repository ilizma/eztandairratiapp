package com.ilizma.schedule.flow.router

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import com.ilizma.schedule.flow.mapper.DayMapper
import com.ilizma.schedule.flow.navigator.ScheduleBackCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.router.ScheduleScreenRouter

class ScheduleScreenRouterImp(
    private val lifecycleOwner: () -> LifecycleOwner,
    private val onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModelLazy: Lazy<ScheduleScreenViewModel>,
    private val mapper: DayMapper,
    private val scheduleBackCloseNavigator: ScheduleBackCloseNavigator,
    private val scheduleDetailNavigator: ScheduleDetailNavigator,
) : ScheduleScreenRouter {

    private val viewModel: ScheduleScreenViewModel by viewModelLazy

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.onBack()
        }
    }

    override fun init() {
        viewModel.navigationAction.observe(
            lifecycleOwner(),
        ) { onNavigationAction(it) }

        onBackPressedDispatcher.addCallback(lifecycleOwner(), onBackPressedCallback)
    }

    private fun onNavigationAction(
        action: ScheduleScreenNavigationAction,
    ) {
        when (action) {
            Back -> scheduleBackCloseNavigator.close()
            is ScheduleScreenNavigationAction.ScheduleDetail -> action.day
                .let { mapper.toFlow(it) }
                .let { scheduleDetailNavigator.navigate(it) }
        }
    }

}