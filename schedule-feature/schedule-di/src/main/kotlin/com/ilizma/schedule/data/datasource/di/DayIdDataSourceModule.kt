package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.DayIdDataSourceImp
import com.ilizma.schedule.view.compose.SCHEDULE_ID
import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DayIdDataSourceModule {

    @Provides
    fun provideDayIdDataSource(
    ): DayIdDataSource = DayIdDataSourceImp(
        dayId = { Settings().getInt(SCHEDULE_ID, -1) },
    )

}