package com.ilizma.net.di

import com.chuckerteam.chucker.api.ChuckerCollector
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val chuckerCollectorModule: Module = module {

    single<ChuckerCollector> {
        ChuckerCollector(
            context = androidContext(),
        )
    }

}