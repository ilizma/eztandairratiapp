package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCase
import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val saveScheduleDetailArgsUseCaseModule: Module = module {

    factory<SaveScheduleDetailArgsUseCase> { SaveScheduleDetailArgsUseCaseImp(repository = get()) }

}