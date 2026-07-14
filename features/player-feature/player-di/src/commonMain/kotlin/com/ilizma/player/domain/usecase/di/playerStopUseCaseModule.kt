package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.usecase.PlayerStopUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val playerStopUseCaseModule: Module = module {

    factory<PlayerStopUseCase> { PlayerStopUseCase(repository = get()) }

}