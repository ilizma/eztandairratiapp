package com.ilizma.schedule.data.datasource.di

import android.content.res.Resources
import com.ilizma.schedule.data.datasource.DaysDataSource
import com.ilizma.schedule.data.datasource.DaysDataSourceImp
import com.ilizma.schedule.data.mapper.DayMapper
import com.ilizma.schedule.data.mapper.DaysArrayMapper
import com.ilizma.resources.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DaysDataSourceModule {

    @Provides
    fun provideDaysDataSource(
        resources: Resources,
    ): DaysDataSource = DaysDataSourceImp(
        daysArray = resources.getStringArray(R.array.days_array),
        mapper = DaysArrayMapper(DayMapper())
    )

}