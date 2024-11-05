package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.mapper.DayListMapper
import com.ilizma.schedule.data.mapper.DayMapper
import com.ilizma.schedule.data.mapper.DaysMapper
import com.ilizma.schedule.data.repository.DaysRepositoryImp
import com.ilizma.schedule.domain.repository.DaysRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val daysRepositoryModule: Module = module {

    factory<DaysRepository> {
        DaysRepositoryImp(
            daysDataSource = get(),
            mapper = DayMapper()
                .let { DayListMapper(it) }
                .let { DaysMapper(it) }
        )
    }

}