package com.ilizma.main.di

import com.ilizma.main.resources.di.resourcesModule
import org.koin.core.module.Module

val mainModules: List<Module> = listOf(
    resourcesModule,
)