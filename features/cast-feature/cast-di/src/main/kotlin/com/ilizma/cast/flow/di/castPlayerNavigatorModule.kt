package com.ilizma.cast.flow.di

import com.ilizma.cast.flow.navigator.CastPlayerNavigator
import com.ilizma.cast.flow.navigator.CastPlayerNavigatorImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val castPlayerNavigatorModule: Module = module {

    factory<CastPlayerNavigator> { CastPlayerNavigatorImp(context = androidContext()) }

}