package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.repository.ScheduleDetailArgsRepositoryImp
import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDetailArgsRepositoryModule {

    @Provides
    fun provideScheduleDetailArgsRepository(
        cache: ScheduleDetailArgsCache,
    ): ScheduleDetailArgsRepository = ScheduleDetailArgsRepositoryImp(
        cache,
    )

}