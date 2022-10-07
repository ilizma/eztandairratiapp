package com.ilizma.schedule.flow.navigator.di

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigator
import com.ilizma.schedule.flow.navigator.ScheduleDetailNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ScheduleDetailNavigatorModule {

    @Provides
    fun provideScheduleDetailNavigator(
        fragment: Fragment,
    ): ScheduleDetailNavigator = ScheduleDetailNavigatorImp(
        fragment.findNavController()
    )

}