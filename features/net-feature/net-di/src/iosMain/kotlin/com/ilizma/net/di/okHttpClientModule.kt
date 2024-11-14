package com.ilizma.net.di

import com.ilizma.net.BaseHttpClient
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

actual val okHttpClientModule: Module = module {

    single<HttpClient> {
        get<BaseHttpClient>().httpClient
    }

}