package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.repository.DayNameRepositoryImp
import com.ilizma.schedule.domain.repository.DayNameRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val dayNameRepositoryModule: Module = module {

    factory<DayNameRepository> { DayNameRepositoryImp(dataSource = get()) }

}