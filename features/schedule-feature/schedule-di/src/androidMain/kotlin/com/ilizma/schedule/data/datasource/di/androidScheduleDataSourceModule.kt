package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.mapper.ProgramDTOListMapper
import com.ilizma.schedule.data.datasource.ScheduleDataSourceImp
import com.ilizma.schedule.data.mapper.ProgramDTOMapper
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.let

private const val HOUR_FORMAT = "%s:00"

actual val scheduleDataSourceModule: Module = module {

    factory<ScheduleDataSource> {
        ScheduleDataSourceImp(
            api = get(),
            mapper = HOUR_FORMAT
                .let { ProgramDTOMapper(it) }
                .let { ProgramDTOListMapper(it) }
                .let { ScheduleStateDTOMapper(it) },
        )
    }

}