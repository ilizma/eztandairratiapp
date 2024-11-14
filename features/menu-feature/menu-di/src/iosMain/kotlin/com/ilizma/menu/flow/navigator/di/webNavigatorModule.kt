package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.WebNavigator
import com.ilizma.menu.flow.navigator.WebNavigatorImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val webNavigatorModule: Module = module {

    factory<WebNavigator> { WebNavigatorImp() }

}