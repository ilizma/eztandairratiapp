package com.ilizma.schedule.data.datasource.di

import android.content.res.Resources
import com.ilizma.api.data.EztandaApi
import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.datasource.ScheduleDataSourceImp
import com.ilizma.schedule.data.mapper.ProgramDTOListMapper
import com.ilizma.schedule.data.mapper.ProgramDTOMapper
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import com.ilizma.resources.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDataSourceModule {

    @Provides
    fun provideScheduleDataSource(
        api: EztandaApi,
        resources: Resources,
    ): ScheduleDataSource = ScheduleDataSourceImp(
        api,
        ScheduleStateDTOMapper(ProgramDTOListMapper(ProgramDTOMapper(resources.getString(R.string.hour)))),
        resources.getString(R.string.unknown_error)
    )

}