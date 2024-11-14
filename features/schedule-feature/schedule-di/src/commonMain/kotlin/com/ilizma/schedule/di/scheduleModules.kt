package com.ilizma.schedule.di

import com.ilizma.schedule.data.cache.di.scheduleDetailArgsCacheModule
import com.ilizma.schedule.data.cache.di.scheduleStateCacheModule
import com.ilizma.schedule.data.datasource.di.dayIdDataSourceModule
import com.ilizma.schedule.data.datasource.di.dayNameDataSourceModule
import com.ilizma.schedule.data.datasource.di.scheduleDataSourceModule
import com.ilizma.schedule.domain.repository.di.dayNameRepositoryModule
import com.ilizma.schedule.domain.repository.di.scheduleDetailArgsRepositoryModule
import com.ilizma.schedule.domain.repository.di.scheduleRepositoryModule
import com.ilizma.schedule.domain.usecase.di.dayNameUseCaseModule
import com.ilizma.schedule.domain.usecase.di.saveScheduleDetailArgsUseCaseModule
import com.ilizma.schedule.domain.usecase.di.scheduleUseCaseModule
import com.ilizma.schedule.flow.navigator.di.scheduleBackNavigatorModule
import com.ilizma.schedule.flow.navigator.di.scheduleDetailCloseNavigatorModule
import com.ilizma.schedule.flow.navigator.di.scheduleDetailNavigatorModule
import com.ilizma.schedule.presentation.viewmodel.di.scheduleDetailScreenViewModelModule
import com.ilizma.schedule.presentation.viewmodel.di.scheduleScreenViewModelModule
import com.ilizma.schedule.view.router.id.scheduleDetailRouterModule
import com.ilizma.schedule.view.router.id.scheduleScreenRouterModule
import org.koin.core.module.Module

val scheduleModules: List<Module> = listOf(
    scheduleDetailArgsCacheModule,
    scheduleStateCacheModule,
    dayIdDataSourceModule,
    dayNameDataSourceModule,
    scheduleDataSourceModule,
    dayNameRepositoryModule,
    scheduleDetailArgsRepositoryModule,
    scheduleRepositoryModule,
    dayNameUseCaseModule,
    saveScheduleDetailArgsUseCaseModule,
    scheduleUseCaseModule,
    scheduleBackNavigatorModule,
    scheduleDetailCloseNavigatorModule,
    scheduleDetailNavigatorModule,
    scheduleDetailScreenViewModelModule,
    scheduleScreenViewModelModule,
    scheduleDetailRouterModule,
    scheduleScreenRouterModule,
)