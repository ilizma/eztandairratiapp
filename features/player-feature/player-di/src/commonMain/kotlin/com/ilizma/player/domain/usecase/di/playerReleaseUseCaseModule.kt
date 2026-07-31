package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.usecase.PlayerReleaseUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val playerReleaseUseCaseModule: Module = module {

    factory<PlayerReleaseUseCase> { PlayerReleaseUseCase(repository = get()) }

}