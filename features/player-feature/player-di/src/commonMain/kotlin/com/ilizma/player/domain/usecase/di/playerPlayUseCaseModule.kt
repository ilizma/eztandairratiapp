package com.ilizma.player.domain.usecase.di

import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerPlayUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val playerPlayUseCaseModule: Module = module {

    factory<PlayerPlayUseCase> { PlayerPlayUseCaseImp(repository = get()) }

}