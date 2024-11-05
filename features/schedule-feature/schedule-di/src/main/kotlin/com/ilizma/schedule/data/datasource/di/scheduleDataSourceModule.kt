package com.ilizma.schedule.data.datasource.di

import android.content.res.Resources
import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.datasource.ScheduleDataSourceImp
import com.ilizma.schedule.data.mapper.ProgramDTOListMapper
import com.ilizma.schedule.data.mapper.ProgramDTOMapper
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import com.ilizma.resources.R
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleDataSourceModule: Module = module {

    factory<ScheduleDataSource> {
        ScheduleDataSourceImp(
            api = get(),
            mapper = get<Resources>().getString(R.string.hour)
                .let { ProgramDTOMapper(it) }
                .let { ProgramDTOListMapper(it) }
                .let { ScheduleStateDTOMapper(it) },
            unknownErrorMessage = get<Resources>().getString(R.string.unknown_error)
        )
    }

}