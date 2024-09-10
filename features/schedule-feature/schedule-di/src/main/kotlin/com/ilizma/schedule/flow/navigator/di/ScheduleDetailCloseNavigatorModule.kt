package com.ilizma.schedule.flow.navigator.di

import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ScheduleDetailCloseNavigatorModule {

    @Provides
    fun provideScheduleDetailCloseNavigator(
    ): ScheduleDetailCloseNavigator = ScheduleDetailCloseNavigatorImp()

}