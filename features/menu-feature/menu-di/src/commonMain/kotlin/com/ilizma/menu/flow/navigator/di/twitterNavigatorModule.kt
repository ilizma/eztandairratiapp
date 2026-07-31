package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.TwitterNavigator
import org.koin.core.module.Module
import org.koin.dsl.module

val twitterNavigatorModule: Module = module {

    factory<TwitterNavigator> { TwitterNavigator() }

}