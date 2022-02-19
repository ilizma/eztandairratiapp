package com.ilizma.schedule.flow.navigator.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilizma.schedule.flow.navigator.ScheduleDetailBackCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailBackCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailBackCloseNavigatorModule {

    @Provides
    fun provideScheduleDetailBackCloseNavigator(
        fragment: Fragment,
    ): ScheduleDetailBackCloseNavigator = ScheduleDetailBackCloseNavigatorImp(
        fragment.findNavController(),
    )

}