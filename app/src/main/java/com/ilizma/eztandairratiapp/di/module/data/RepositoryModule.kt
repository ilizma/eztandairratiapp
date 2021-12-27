package com.ilizma.eztandairratiapp.di.module.data

import com.ilizma.domain.repository.ScheduleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun schedule(
        scheduleRepositoryImpl: ScheduleRepositoryImpl,
    ): ScheduleRepository

}