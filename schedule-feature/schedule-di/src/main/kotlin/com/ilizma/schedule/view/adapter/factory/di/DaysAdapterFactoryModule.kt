package com.ilizma.schedule.view.adapter.factory.di

import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.view.adapter.factory.DaysAdapterFactoryImp
import com.ilizma.schedule.view.bind.factory.DayBinderFactory
import com.ilizma.schedule.view.viewholder.factory.DayViewHolderFactory
import com.ilizma.view.adapter.factory.AdapterFactory
import com.ilizma.view.adapter.util.ItemDiffUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object DaysAdapterFactoryModule {

    @Provides
    fun provideDaysAdapterFactory(
        binderFactory: DayBinderFactory,
        diffUtil: ItemDiffUtil<Day>,
        viewHolderFactory: DayViewHolderFactory,
    ): AdapterFactory<Day> = DaysAdapterFactoryImp(
        binderFactory = binderFactory,
        diffUtil = diffUtil,
        viewHolderFactory = viewHolderFactory,
    )

}