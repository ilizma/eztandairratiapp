package com.ilizma.schedule.data.datasource.di

import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.data.datasource.DayNameDataSourceImp
import com.ilizma.schedule.view.compose.SCHEDULE_NAME
import com.russhwolf.settings.Settings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DayNameDataSourceModule {

    @Provides
    fun provideDayNameDataSource(
    ): DayNameDataSource = DayNameDataSourceImp(
        dayName = { Settings().getString(SCHEDULE_NAME, "") },
    )

}