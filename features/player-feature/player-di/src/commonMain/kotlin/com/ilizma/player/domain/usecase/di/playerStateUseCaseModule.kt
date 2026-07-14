package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.usecase.PlayerStateUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val playerStateUseCaseModule: Module = module {

    factory<PlayerStateUseCase> { PlayerStateUseCase(repository = get()) }

}