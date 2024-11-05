package com.ilizma.errormanagement.di

import com.ilizma.errormanagement.view.bind.di.crashFragmentBinderModule
import org.koin.core.module.Module

val errorManagementModules: List<Module> = listOf(
    crashFragmentBinderModule,
)