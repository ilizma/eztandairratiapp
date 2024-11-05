package com.ilizma.menu.view.router.di

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.menu.flow.router.MenuScreenRouterImp
import com.ilizma.menu.view.router.MenuScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val menuScreenRouterModule: Module = module {

    scope<MainActivity> {
        scoped<MenuScreenRouter> {
            MenuScreenRouterImp(
                lifecycleCoroutineScope = get<ComponentActivity>().lifecycleScope,
                instagramNavigator = get(),
                twitterNavigator = get(),
                facebookNavigator = get(),
                phoneNavigator = get(),
                webNavigator = get(),
                menuBackNavigator = get(),
            )
        }
    }

}