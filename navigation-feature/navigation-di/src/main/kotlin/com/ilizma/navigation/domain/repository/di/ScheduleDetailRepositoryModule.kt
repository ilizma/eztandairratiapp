package com.ilizma.navigation.domain.repository.di

import com.ilizma.navigation.domain.repository.ScheduleDetailRepository
import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.mapper.ScheduleDetailArgsMapper
import com.ilizma.schedule.data.repository.ScheduleDetailRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDetailRepositoryModule {

    @Provides
    fun provideScheduleDetailRepository(
        cache: ScheduleDetailArgsCache,
    ): ScheduleDetailRepository = ScheduleDetailRepositoryImp(
        cache = cache,
        mapper = ScheduleDetailArgsMapper(),
    )

}