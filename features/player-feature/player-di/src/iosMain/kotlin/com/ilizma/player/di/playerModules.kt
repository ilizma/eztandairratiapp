package com.ilizma.player.di

import com.ilizma.player.data.datasource.di.playerDataSourceModule
import com.ilizma.player.domain.repository.di.playerRepositoryModule
import com.ilizma.player.domain.usecase.di.playerPlayUseCaseModule
import com.ilizma.player.domain.usecase.di.playerStateUseCaseModule
import com.ilizma.player.domain.usecase.di.playerStopUseCaseModule
import com.ilizma.player.flow.navigator.di.radioCloseNavigatorModule
import com.ilizma.player.framework.di.playerFrameworkModule
import com.ilizma.player.framework.helper.di.playerHelperModule
import com.ilizma.player.presentation.viewmodel.di.radioScreenViewModelModule
import com.ilizma.player.view.router.di.radioScreenRouterModule
import org.koin.core.module.Module

actual val playerModules: List<Module> = listOf(
    playerDataSourceModule,
    playerRepositoryModule,
    playerPlayUseCaseModule,
    playerStateUseCaseModule,
    playerStopUseCaseModule,
    radioCloseNavigatorModule,
    playerFrameworkModule,
    playerHelperModule,
    radioScreenViewModelModule,
    radioScreenRouterModule,
)