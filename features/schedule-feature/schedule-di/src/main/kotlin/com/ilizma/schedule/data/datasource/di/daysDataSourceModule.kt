package com.ilizma.schedule.data.datasource.di

import android.content.res.Resources
import com.ilizma.schedule.data.datasource.DaysDataSource
import com.ilizma.schedule.data.datasource.DaysDataSourceImp
import com.ilizma.schedule.data.mapper.DayMapper
import com.ilizma.schedule.data.mapper.DaysArrayMapper
import com.ilizma.resources.R
import org.koin.core.module.Module
import org.koin.dsl.module

val daysDataSourceModule: Module = module {

    factory<DaysDataSource> {
        DaysDataSourceImp(
            daysArray = get<Resources>().getStringArray(R.array.days_array),
            mapper = DaysArrayMapper(DayMapper())
        )
    }

}