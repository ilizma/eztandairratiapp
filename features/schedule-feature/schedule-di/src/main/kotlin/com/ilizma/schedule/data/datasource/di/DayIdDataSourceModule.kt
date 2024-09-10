package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.DayIdDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DayIdDataSourceModule {

    @Provides
    fun provideDayIdDataSource(
        cache: ScheduleDetailArgsCache,
    ): DayIdDataSource = DayIdDataSourceImp(
        dayId = { cache.get()?.id ?: throw NullPointerException("cached id can not be null") },
    )

}