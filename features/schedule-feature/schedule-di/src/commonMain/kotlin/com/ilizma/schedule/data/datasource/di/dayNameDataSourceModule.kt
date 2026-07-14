package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.datasource.DayNameDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val dayNameDataSourceModule: Module = module {

    factory<DayNameDataSource> {
        DayNameDataSource(
            dayName = {
                @Suppress("UndeclaredKoinUsage")
                get<ScheduleDetailArgsCache>().get()
                    ?.name
                    ?: throw NullPointerException("cached name can not be null")
            },
        )
    }

}