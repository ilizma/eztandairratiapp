package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val saveScheduleDetailArgsUseCaseModule: Module = module {

    factory<SaveScheduleDetailArgsUseCase> { SaveScheduleDetailArgsUseCase(repository = get()) }

}