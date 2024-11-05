package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.data.datasource.DayNameDataSourceImp
import org.koin.core.module.Module
import org.koin.dsl.module

val dayNameDataSourceModule: Module = module {

    factory<DayNameDataSource> {
        DayNameDataSourceImp(
            dayName = {
                get<ScheduleDetailArgsCache>().get()
                    ?.name
                    ?: throw NullPointerException("cached name can not be null")
            },
        )
    }

}