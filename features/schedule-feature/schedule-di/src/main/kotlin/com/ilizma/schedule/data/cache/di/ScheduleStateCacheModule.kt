package com.ilizma.schedule.data.cache.di

import com.ilizma.schedule.data.cache.ScheduleStateCache
import com.ilizma.schedule.data.cache.ScheduleStateCacheImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScheduleStateCacheModule {

    @Provides
    @Singleton
    fun provideScheduleStateCache(
    ): ScheduleStateCache = ScheduleStateCacheImp()

}