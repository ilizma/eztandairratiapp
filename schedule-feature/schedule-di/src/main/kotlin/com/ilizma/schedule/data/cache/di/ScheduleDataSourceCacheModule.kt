package com.ilizma.schedule.data.cache.di

import com.ilizma.schedule.data.cache.ScheduleDataSourceCache
import com.ilizma.schedule.data.cache.ScheduleDataSourceCacheImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDataSourceCacheModule {

    @Provides
    fun provideScheduleDataSourceCache(
    ): ScheduleDataSourceCache = ScheduleDataSourceCacheImp()

}