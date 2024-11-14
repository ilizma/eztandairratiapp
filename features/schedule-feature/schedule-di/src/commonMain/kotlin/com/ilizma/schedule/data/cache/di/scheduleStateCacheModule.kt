package com.ilizma.schedule.data.cache.di

import com.ilizma.schedule.data.cache.ScheduleStateCache
import com.ilizma.schedule.data.cache.ScheduleStateCacheImp
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleStateCacheModule: Module = module {

    single<ScheduleStateCache> { ScheduleStateCacheImp() }

}