package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.MenuBackNavigator
import com.ilizma.menu.flow.navigator.MenuBackNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

val menuBackNavigatorModule: Module = module {

    factory<MenuBackNavigator> { MenuBackNavigatorImp() }

}