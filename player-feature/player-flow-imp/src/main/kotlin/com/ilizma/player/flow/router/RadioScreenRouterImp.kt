package com.ilizma.player.flow.router

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import com.ilizma.player.flow.navigator.RadioBackCloseNavigator
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter

class RadioScreenRouterImp(
    private val lifecycleOwner: () -> LifecycleOwner,
    private val onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModelLazy: Lazy<RadioScreenViewModel>,
    private val radioBackCloseNavigator: RadioBackCloseNavigator,
) : RadioScreenRouter {

    private val viewModel: RadioScreenViewModel by viewModelLazy

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
        action: RadioScreenNavigationAction,
    ) {
        when (action) {
            Back -> radioBackCloseNavigator.close()
        }
    }

}