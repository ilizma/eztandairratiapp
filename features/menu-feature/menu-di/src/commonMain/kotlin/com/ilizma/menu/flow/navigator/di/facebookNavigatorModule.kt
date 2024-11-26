package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.FacebookNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val facebookNavigatorModule: Module = module {

    factory<FacebookNavigator> { FacebookNavigatorImp() }

}