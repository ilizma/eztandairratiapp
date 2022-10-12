package com.ilizma.schedule.view.viewholder.factory.di

import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DayViewHolderFactoryModule {

    @Provides
    fun provideDayViewHolderFactory(
    ): DayViewHolderFactory = DayViewHolderFactory()

}