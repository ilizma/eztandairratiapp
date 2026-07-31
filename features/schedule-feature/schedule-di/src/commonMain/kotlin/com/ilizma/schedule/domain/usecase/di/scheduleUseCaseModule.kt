package com.ilizma.schedule.domain.usecase.di

import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleUseCaseModule: Module = module {

    factory<ScheduleUseCase> { ScheduleUseCase(repository = get()) }

}