package com.ilizma.schedule.data.datasource.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.DayIdDataSourceImp
import com.ilizma.schedule.view.fragment.ScheduleDetailFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DayIdDataSourceModule {

    @Provides
    fun provideDayIdDataSource(
        fragment: Fragment,
    ): DayIdDataSource = fragment.navArgs<ScheduleDetailFragmentArgs>().value.day.id
        .let { DayIdDataSourceImp(it) }

}