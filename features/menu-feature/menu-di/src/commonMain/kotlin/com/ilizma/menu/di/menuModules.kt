package com.ilizma.menu.di

import com.ilizma.menu.flow.navigator.di.facebookNavigatorModule
import com.ilizma.menu.flow.navigator.di.instagramNavigatorModule
import com.ilizma.menu.flow.navigator.di.menuBackNavigatorModule
import com.ilizma.menu.flow.navigator.di.phoneNavigatorModule
import com.ilizma.menu.flow.navigator.di.twitterNavigatorModule
import com.ilizma.menu.flow.navigator.di.webNavigatorModule
import com.ilizma.menu.flow.navigator.di.whatsAppNavigatorModule
import com.ilizma.menu.presentation.viewmodel.di.menuScreenViewModelModule
import com.ilizma.menu.view.router.di.menuScreenRouterModule
import org.koin.core.module.Module

val menuModules: List<Module> = listOf(
    facebookNavigatorModule,
    instagramNavigatorModule,
    menuBackNavigatorModule,
    phoneNavigatorModule,
    whatsAppNavigatorModule,
    twitterNavigatorModule,
    webNavigatorModule,
    menuScreenViewModelModule,
    menuScreenRouterModule,
)