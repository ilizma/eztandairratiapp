package com.ilizma.player.view.router.di

import com.ilizma.player.flow.router.RadioScreenRouterImp
import com.ilizma.player.view.router.RadioScreenRouter
import org.koin.core.module.Module
import org.koin.dsl.module

actual val radioScreenRouterModule: Module = module {

    factory<RadioScreenRouter> { RadioScreenRouterImp(radioCloseNavigator = get()) }

}