package com.ilizma.net.di

import com.ilizma.net.BaseHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val baseHttpClientModule: Module = module {

    single<BaseHttpClient> {
        BaseHttpClient()
    }

}