package com.ilizma.schedule.view.adapter.factory.di

import com.ilizma.schedule.view.adapter.factory.DaysAdapterFactory
import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DaysAdapterFactoryModule {

    @Provides
    fun provideDaysAdapterFactory(
        viewHolderFactory: DayViewHolderFactory
    ): DaysAdapterFactory = DaysAdapterFactory(
        viewHolderFactory,
    )

}