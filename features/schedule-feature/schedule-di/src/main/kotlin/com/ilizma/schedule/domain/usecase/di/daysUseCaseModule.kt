package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.usecase.DaysUseCase
import com.ilizma.schedule.domain.usecase.DaysUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val daysUseCaseModule: Module = module {

    factory<DaysUseCase> { DaysUseCaseImp(repository = get()) }

}