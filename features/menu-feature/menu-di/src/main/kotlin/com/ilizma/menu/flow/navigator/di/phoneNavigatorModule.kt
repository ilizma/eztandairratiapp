package com.ilizma.menu.flow.navigator.di

import com.ilizma.menu.flow.navigator.PhoneNavigator
import com.ilizma.menu.flow.navigator.PhoneNavigatorImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val phoneNavigatorModule: Module = module {

    factory<PhoneNavigator> { PhoneNavigatorImp(context = androidContext()) }

}