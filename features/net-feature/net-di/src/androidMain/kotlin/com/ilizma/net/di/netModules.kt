package com.ilizma.net.di

import org.koin.core.module.Module

actual val netModules: List<Module> = listOf(
    baseHttpClientModule,
    baseKtorfitModule,
    chuckerCollectorModule,
    ktorfitModule,
    okHttpClientModule,
)