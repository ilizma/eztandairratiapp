package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.cache.ScheduleStateCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.mapper.ProgramListMapper
import com.ilizma.schedule.data.mapper.ProgramMapper
import com.ilizma.schedule.data.mapper.ScheduleStateMapper
import com.ilizma.schedule.data.repository.ScheduleRepositoryImp
import com.ilizma.schedule.domain.repository.ScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleRepositoryModule {

    @Provides
    fun provideScheduleRepository(
        dataSource: ScheduleDataSource,
        dayIdDataSource: DayIdDataSource,
        cache: ScheduleStateCache,
    ): ScheduleRepository = ScheduleRepositoryImp(
        dataSource = dataSource,
        dayIdDataSource = dayIdDataSource,
        cache = cache,
        mapper = ScheduleStateMapper(ProgramListMapper(ProgramMapper())),
    )

}