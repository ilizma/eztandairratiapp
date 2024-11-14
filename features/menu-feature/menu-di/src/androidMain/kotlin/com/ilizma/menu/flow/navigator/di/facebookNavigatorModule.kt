package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.FacebookNavigatorImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val facebookNavigatorModule: Module = module {

    factory<FacebookNavigator> { FacebookNavigatorImp(context = androidContext()) }

}