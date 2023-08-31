package com.ilizma.schedule.flow.navigator.di

import com.ilizma.schedule.flow.navigator.ScheduleBackNavigator
import com.ilizma.schedule.flow.navigator.ScheduleBackNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleBackNavigatorModule {

    @Provides
    fun provideScheduleBackNavigator(
    ): ScheduleBackNavigator = ScheduleBackNavigatorImp()

}