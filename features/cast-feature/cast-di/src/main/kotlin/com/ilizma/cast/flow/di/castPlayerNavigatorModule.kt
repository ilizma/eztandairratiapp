package com.ilizma.cast.flow.di

import com.ilizma.cast.flow.navigator.CastPlayerNavigator
import com.ilizma.cast.flow.navigator.CastPlayerNavigatorImp
import com.ilizma.main.view.activity.MainActivity
import org.koin.core.module.Module
import org.koin.dsl.module

val castPlayerNavigatorModule: Module = module {

    scope<MainActivity> {
        scoped<CastPlayerNavigator> { CastPlayerNavigatorImp(activity = get()) }
    }

}