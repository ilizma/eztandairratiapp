package com.ilizma.menu.view.router.di

import com.ilizma.menu.flow.router.MenuScreenRouter
import com.ilizma.menu.view.router.MenuScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val menuScreenRouterModule: Module = module {

    factory<MenuScreenRouter> {
        MenuScreenRouter(
            instagramNavigator = get(),
            twitterNavigator = get(),
            facebookNavigator = get(),
            phoneNavigator = get(),
            whatsAppNavigator = get(),
            webNavigator = get(),
            menuBackNavigator = get(),
        )
    }

}