package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.mapper.ProgramListMapper
import com.ilizma.schedule.data.mapper.ProgramMapper
import com.ilizma.schedule.data.mapper.ScheduleStateMapper
import com.ilizma.schedule.data.repository.ScheduleRepositoryImp
import com.ilizma.schedule.domain.repository.ScheduleRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val scheduleRepositoryModule: Module = module {

    factory<ScheduleRepository> {
        ScheduleRepositoryImp(
            dataSource = get(),
            dayIdDataSource = get(),
            cache = get(),
            mapper = ProgramMapper()
                .let { ProgramListMapper(it) }
                .let { ScheduleStateMapper(it) },
        )
    }

}