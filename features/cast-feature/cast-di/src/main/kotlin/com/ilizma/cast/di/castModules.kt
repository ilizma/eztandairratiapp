package com.ilizma.cast.di

import com.ilizma.cast.flow.di.castPlayerNavigatorModule
import com.ilizma.cast.framework.di.castFrameworkModule
import org.koin.core.module.Module

val castModules: List<Module> = listOf(
    castPlayerNavigatorModule,
    castFrameworkModule,
)