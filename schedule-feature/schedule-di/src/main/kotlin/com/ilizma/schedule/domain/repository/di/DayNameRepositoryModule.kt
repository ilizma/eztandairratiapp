package com.ilizma.schedule.domain.repository.di

import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.data.repository.DayNameRepositoryImp
import com.ilizma.schedule.domain.repository.DayNameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DayNameRepositoryModule {

    @Provides
    fun provideDayNameRepository(
        dataSource: DayNameDataSource,
    ): DayNameRepository = DayNameRepositoryImp(
        dataSource
    )

}