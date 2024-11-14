package com.ilizma.main.resources.di

import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val resourcesModule: Module = module {

    single<Resources> { androidContext().resources }

}