package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val playerPlayUseCaseModule: Module = module {

    factory<PlayerPlayUseCase> { PlayerPlayUseCase(repository = get()) }

}