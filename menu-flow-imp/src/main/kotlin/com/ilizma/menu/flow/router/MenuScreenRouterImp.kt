package com.ilizma.menu.flow.router

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.LifecycleOwner
import com.ilizma.menu.flow.navigator.*
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.*
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter

class MenuScreenRouterImp(
    private val lifecycleOwner: () -> LifecycleOwner,
    private val onBackPressedDispatcher: OnBackPressedDispatcher,
    viewModelLazy: Lazy<MenuScreenViewModel>,
    private val twitterNavigator: TwitterNavigator,
    private val facebookNavigator: FacebookNavigator,
    private val whatsappNavigator: WhatsappNavigator,
    private val phoneNavigator: PhoneNavigator,
    private val webNavigator: WebNavigator,
    private val menuBackCloseNavigator: MenuBackCloseNavigator,
) : MenuScreenRouter {

    private val viewModel: MenuScreenViewModel by viewModelLazy

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
        action: MenuNavigationAction,
    ) {
        when (action) {
            Twitter -> twitterNavigator.navigate()
            Facebook -> facebookNavigator.navigate()
            Whatsapp -> whatsappNavigator.navigate()
            Phone -> phoneNavigator.navigate()
            Web -> webNavigator.navigate()
            Back -> menuBackCloseNavigator.close()
        }
    }

}