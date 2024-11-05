package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.repository.ScheduleDetailArgsRepositoryImp
import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDetailArgsRepositoryModule: Module = module {

    factory<ScheduleDetailArgsRepository> { ScheduleDetailArgsRepositoryImp(cache = get()) }

}