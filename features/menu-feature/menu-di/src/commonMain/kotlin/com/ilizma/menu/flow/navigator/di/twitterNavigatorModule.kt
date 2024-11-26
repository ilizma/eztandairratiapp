package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.TwitterNavigator
import com.ilizma.menu.flow.navigator.TwitterNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val twitterNavigatorModule: Module = module {

    factory<TwitterNavigator> { TwitterNavigatorImp() }

}