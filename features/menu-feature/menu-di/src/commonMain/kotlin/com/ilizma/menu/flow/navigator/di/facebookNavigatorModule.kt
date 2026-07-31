package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.FacebookNavigator
import org.koin.core.module.Module
import org.koin.dsl.module

val facebookNavigatorModule: Module = module {

    factory<FacebookNavigator> { FacebookNavigator() }

}