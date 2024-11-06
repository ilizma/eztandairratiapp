package com.ilizma.net.di

import com.ilizma.net.BaseHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val baseHttpClientModule: Module = module {

    single<BaseHttpClient> {
        BaseHttpClient(
            chuckerCollector = get(),
            context = androidContext(),
        )
    }

}