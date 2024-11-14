package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.DayNameUseCaseImp
import org.koin.core.module.Module
import org.koin.dsl.module

val dayNameUseCaseModule: Module = module {

    factory<DayNameUseCase> { DayNameUseCaseImp(repository = get()) }

}