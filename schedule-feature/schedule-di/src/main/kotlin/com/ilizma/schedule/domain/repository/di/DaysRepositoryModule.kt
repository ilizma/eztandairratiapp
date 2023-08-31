package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.datasource.DaysDataSource
import com.ilizma.schedule.data.mapper.DayListMapper
import com.ilizma.schedule.data.mapper.DayMapper
import com.ilizma.schedule.data.mapper.DaysMapper
import com.ilizma.schedule.data.repository.DaysRepositoryImp
import com.ilizma.schedule.domain.repository.DaysRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DaysRepositoryModule {

    @Provides
    fun provideDaysRepository(
        daysDataSource: DaysDataSource,
    ): DaysRepository = DaysRepositoryImp(
        daysDataSource,
        DaysMapper(DayListMapper(DayMapper())),
    )

}