package com.ilizma.schedule.view.adapter.factory.di

import com.ilizma.schedule.view.adapter.factory.ProgramsAdapterFactory
import com.ilizma.schedule.view.viewholder.factory.ProgramViewHolderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ProgramsAdapterFactoryModule {

    @Provides
    fun provideProgramsAdapterFactory(
        viewHolderFactory: ProgramViewHolderFactory
    ): ProgramsAdapterFactory = ProgramsAdapterFactory(
        viewHolderFactory,
    )

}