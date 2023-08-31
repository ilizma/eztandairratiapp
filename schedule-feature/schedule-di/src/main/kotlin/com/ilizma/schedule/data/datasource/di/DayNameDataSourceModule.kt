package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.data.datasource.DayNameDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DayNameDataSourceModule {

    @Provides
    fun provideDayNameDataSource(
        cache: ScheduleDetailArgsCache,
    ): DayNameDataSource = DayNameDataSourceImp(
        dayName = { cache.get()?.name ?: throw NullPointerException("cached name can not be null") },
    )

}