package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.InstagramNavigator
import com.ilizma.menu.flow.navigator.InstagramNavigatorImp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val instagramNavigatorModule: Module = module {

    factory<InstagramNavigator> { InstagramNavigatorImp(context = androidContext()) }

}