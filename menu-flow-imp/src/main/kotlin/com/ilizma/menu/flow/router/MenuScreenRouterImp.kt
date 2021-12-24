package com.ilizma.menu.flow.router

import androidx.lifecycle.LifecycleOwner
import com.ilizma.menu.flow.navigator.*
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.*
import com.ilizma.menu.presentation.viewmodel.MenuViewModel
import com.ilizma.menu.view.router.MenuScreenRouter

class MenuScreenRouterImp(
    private val lifecycleOwner: () -> LifecycleOwner,
    viewModelLazy: Lazy<MenuViewModel>,
    private val twitterNavigator: TwitterNavigator,
    private val facebookNavigator: FacebookNavigator,
    private val whatsappNavigator: WhatsappNavigator,
    private val phoneNavigator: PhoneNavigator,
    private val webNavigator: WebNavigator,
) : MenuScreenRouter {

    private val viewModel: MenuViewModel by viewModelLazy

    override fun init() {
        viewModel.navigationAction.observe(
            lifecycleOwner(),
        ) { onNavigationAction(it) }
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
        }
    }

}