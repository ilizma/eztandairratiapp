package com.ilizma.schedule.data.cache.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.cache.ScheduleDetailArgsCacheImp
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDetailArgsCacheModule: Module = module {

    single<ScheduleDetailArgsCache> { ScheduleDetailArgsCacheImp() }

}