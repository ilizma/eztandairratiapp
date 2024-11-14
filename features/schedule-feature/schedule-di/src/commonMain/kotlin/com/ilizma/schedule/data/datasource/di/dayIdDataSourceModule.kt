package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.DayIdDataSourceImp
import org.koin.core.module.Module
import org.koin.dsl.module

val dayIdDataSourceModule: Module = module {

    factory<DayIdDataSource> {
        DayIdDataSourceImp(
            dayId = {
                get<ScheduleDetailArgsCache>().get()
                    ?.id
                    ?: throw NullPointerException("cached id can not be null")
            },
        )
    }

}