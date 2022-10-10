package com.ilizma.schedule.flow.navigator.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilizma.schedule.flow.navigator.ScheduleBackCloseNavigator
import com.ilizma.schedule.flow.navigator.ScheduleBackCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleBackCloseNavigatorModule {

    @Provides
    fun provideScheduleBackCloseNavigator(
        fragment: Fragment,
    ): ScheduleBackCloseNavigator = ScheduleBackCloseNavigatorImp(
        navController = fragment.findNavController(),
    )

}