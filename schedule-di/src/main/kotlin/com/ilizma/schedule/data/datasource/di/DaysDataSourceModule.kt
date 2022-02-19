package com.ilizma.schedule.data.datasource.di

import android.content.res.Resources
import com.ilizma.api.data.EztandaApi
import com.ilizma.schedule.data.datasource.DaysDataSource
import com.ilizma.schedule.data.datasource.DaysDataSourceImp
import com.ilizma.schedule.data.mapper.DayMapper
import com.ilizma.schedule.data.mapper.DaysArrayMapper
import com.ilizma.schedule.di.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DaysDataSourceModule {

    @Provides
    fun provideDaysDataSource(
        api: EztandaApi,
        resources: Resources,
    ): DaysDataSource = DaysDataSourceImp(
        daysArray = resources.getStringArray(R.array.days_array),
        mapper = DaysArrayMapper(DayMapper())
    )

}