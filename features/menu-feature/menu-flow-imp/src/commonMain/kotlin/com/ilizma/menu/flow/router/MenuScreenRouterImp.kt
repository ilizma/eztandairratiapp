package com.ilizma.menu.flow.router

import androidx.compose.ui.platform.UriHandler
import androidx.navigation.NavHostController
import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.InstagramNavigator
import com.ilizma.menu.flow.navigator.MenuBackNavigator
import com.ilizma.menu.flow.navigator.PhoneNavigator
import com.ilizma.menu.flow.navigator.TwitterNavigator
import com.ilizma.menu.flow.navigator.WebNavigator
import com.ilizma.menu.flow.navigator.WhatsAppNavigator
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.Back
import com.ilizma.menu.presentation.model.MenuNavigationAction.Facebook
import com.ilizma.menu.presentation.model.MenuNavigationAction.Instagram
import com.ilizma.menu.presentation.model.MenuNavigationAction.Phone
import com.ilizma.menu.presentation.model.MenuNavigationAction.Twitter
import com.ilizma.menu.presentation.model.MenuNavigationAction.Web
import com.ilizma.menu.presentation.model.MenuNavigationAction.WhatsApp
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuScreenRouterImp(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val instagramNavigator: InstagramNavigator,
    private val twitterNavigator: TwitterNavigator,
    private val facebookNavigator: FacebookNavigator,
    private val phoneNavigator: PhoneNavigator,
    private val whatsAppNavigator: WhatsAppNavigator,
    private val webNavigator: WebNavigator,
    private val menuBackNavigator: MenuBackNavigator,
) : MenuScreenRouter {

    override fun init(
        uriHandler: UriHandler,
        coroutineScope: CoroutineScope,
        viewModel: MenuScreenViewModel,
        navController: NavHostController,
    ) {
        coroutineScope.launch(dispatcher) {
            viewModel.navigationAction.collect {
                onNavigationAction(
                    uriHandler = uriHandler,
                    navController = navController,
                    action = it,
                )
            }
        }
    }

    private fun onNavigationAction(
        uriHandler: UriHandler,
        navController: NavHostController,
        action: MenuNavigationAction,
    ) {
        when (action) {
            Instagram -> instagramNavigator.navigate(uriHandler)
            Twitter -> twitterNavigator.navigate(uriHandler)
            Facebook -> facebookNavigator.navigate(uriHandler)
            Phone -> phoneNavigator.navigate()
            WhatsApp -> whatsAppNavigator.navigate()
            Web -> webNavigator.navigate(uriHandler)
            Back -> menuBackNavigator.back(navController)
        }
    }

}