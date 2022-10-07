package com.ilizma.schedule.flow.router

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import com.ilizma.schedule.flow.navigator.ScheduleDetailBackCloseNavigator
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction.Back
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter

class ScheduleDetailRouterImp(
    private val lifecycleOwner: () -> LifecycleOwner,
    private val onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModelLazy: Lazy<ScheduleDetailViewModel>,
    private val navigator: ScheduleDetailBackCloseNavigator,
) : ScheduleDetailRouter {

    private val viewModel: ScheduleDetailViewModel by viewModelLazy

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
        action: ScheduleDetailNavigationAction,
    ) {
        when (action) {
            Back -> navigator.close()
        }
    }

}