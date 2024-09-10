package com.ilizma.schedule.data.cache.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.cache.ScheduleDetailArgsCacheImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ScheduleDetailArgsCacheModule {

    @Provides
    @Singleton
    fun provideScheduleDetailArgsCache(
    ): ScheduleDetailArgsCache = ScheduleDetailArgsCacheImp()

}