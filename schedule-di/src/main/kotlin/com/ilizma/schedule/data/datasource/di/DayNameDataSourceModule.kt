package com.ilizma.schedule.data.datasource.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.data.datasource.DayNameDataSourceImp
import com.ilizma.schedule.view.fragment.ScheduleDetailFragmentArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DayNameDataSourceModule {

    @Provides
    fun provideDayNameDataSource(
        fragment: Fragment,
    ): DayNameDataSource = fragment.navArgs<ScheduleDetailFragmentArgs>().value.day.name
        .let { DayNameDataSourceImp(it) }

}