package com.ilizma.player.flow.router

import androidx.lifecycle.LifecycleCoroutineScope
import com.ilizma.cast.flow.navigator.CastPlayerNavigator
import com.ilizma.player.flow.navigator.RadioCloseNavigator
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.Back
import com.ilizma.player.presentation.model.RadioScreenNavigationAction.CastPlayer
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import kotlinx.coroutines.launch

class RadioScreenRouterImp(
    private val lifecycleCoroutineScope: LifecycleCoroutineScope,
    private val radioCloseNavigator: RadioCloseNavigator,
    private val castPlayerNavigator: CastPlayerNavigator,
) : RadioScreenRouter {

    override fun init(
        viewModel: RadioScreenViewModel,
    ) {
        lifecycleCoroutineScope.launch {
            viewModel.navigationAction.collect { onNavigationAction(it) }
        }
    }

    private fun onNavigationAction(
        action: RadioScreenNavigationAction,
    ) {
        when (action) {
            Back -> radioCloseNavigator.close()
            CastPlayer -> castPlayerNavigator.navigate()
        }
    }

}