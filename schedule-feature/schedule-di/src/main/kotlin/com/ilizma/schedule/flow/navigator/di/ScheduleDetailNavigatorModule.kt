package com.ilizma.schedule.flow.navigator.di

import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDetailNavigatorModule {

    @Provides
    fun provideScheduleDetailNavigator(
    ): ScheduleDetailNavigator = ScheduleDetailNavigatorImp()

}