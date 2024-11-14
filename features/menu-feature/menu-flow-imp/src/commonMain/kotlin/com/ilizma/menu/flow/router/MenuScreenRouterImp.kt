package com.ilizma.menu.flow.router

import androidx.lifecycle.coroutineScope
import androidx.navigation.NavHostController
import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.InstagramNavigator
import com.ilizma.menu.flow.navigator.MenuBackNavigator
import com.ilizma.menu.flow.navigator.PhoneNavigator
import com.ilizma.menu.flow.navigator.TwitterNavigator
import com.ilizma.menu.flow.navigator.WebNavigator
import com.ilizma.menu.presentation.model.MenuNavigationAction
import com.ilizma.menu.presentation.model.MenuNavigationAction.Back
import com.ilizma.menu.presentation.model.MenuNavigationAction.Facebook
import com.ilizma.menu.presentation.model.MenuNavigationAction.Instagram
import com.ilizma.menu.presentation.model.MenuNavigationAction.Phone
import com.ilizma.menu.presentation.model.MenuNavigationAction.Twitter
import com.ilizma.menu.presentation.model.MenuNavigationAction.Web
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MenuScreenRouterImp(
    private val instagramNavigator: InstagramNavigator,
    private val twitterNavigator: TwitterNavigator,
    private val facebookNavigator: FacebookNavigator,
    private val phoneNavigator: PhoneNavigator,
    private val webNavigator: WebNavigator,
    private val menuBackNavigator: MenuBackNavigator,
) : MenuScreenRouter {

    override fun init(
        coroutineScope: CoroutineScope,
        viewModel: MenuScreenViewModel,
        navController: NavHostController,
    ) {
        coroutineScope.launch {
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
        action: MenuNavigationAction,
    ) {
        when (action) {
            Instagram -> instagramNavigator.navigate()
            Twitter -> twitterNavigator.navigate()
            Facebook -> facebookNavigator.navigate()
            Phone -> phoneNavigator.navigate()
            Web -> webNavigator.navigate()
            Back -> menuBackNavigator.back(navController)
        }
    }

}