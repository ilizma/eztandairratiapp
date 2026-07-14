package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val dayIdDataSourceModule: Module = module {

    factory<DayIdDataSource> {
        DayIdDataSource(
            dayId = {
                @Suppress("UndeclaredKoinUsage")
                get<ScheduleDetailArgsCache>().get()
                    ?.id
                    ?: throw NullPointerException("cached id can not be null")
            },
        )
    }

}