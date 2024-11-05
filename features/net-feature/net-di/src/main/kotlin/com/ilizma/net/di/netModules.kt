package com.ilizma.net.di

import org.koin.core.module.Module

val netModules: List<Module> = listOf(
    baseHttpClientModule,
    baseKtorfitModule,
    chuckerCollectorModule,
    ktorfitModule,
    okHttpClientModule,
)