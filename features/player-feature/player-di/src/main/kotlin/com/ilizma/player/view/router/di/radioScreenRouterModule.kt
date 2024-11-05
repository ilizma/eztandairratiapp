package com.ilizma.player.view.router.di

import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.ilizma.main.view.activity.MainActivity
import com.ilizma.player.flow.router.RadioScreenRouterImp
import com.ilizma.player.view.router.RadioScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

val radioScreenRouterModule: Module = module {

    scope<MainActivity> {
        scoped<RadioScreenRouter> {
            RadioScreenRouterImp(
                lifecycleCoroutineScope = get<ComponentActivity>().lifecycleScope,
                radioCloseNavigator = get(),
                castPlayerNavigator = get()
            )
        }
    }

}