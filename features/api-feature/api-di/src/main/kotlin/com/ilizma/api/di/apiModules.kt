package com.ilizma.api.di

import com.ilizma.api.data.di.eztandaApiModule
import org.koin.core.module.Module

val apiModules: List<Module> = listOf(
    eztandaApiModule,
)