package com.ilizma.net.di

import EztandaIrratiapp.net_di.BuildConfig
import com.ilizma.net.BaseKtorfit
import org.koin.core.module.Module
import org.koin.dsl.module

val baseKtorfitModule: Module = module {

    single<BaseKtorfit> {
        BaseKtorfit(
            httpClient = get(),
            baseUrl = BuildConfig.BASE_URL,
        )
    }

}