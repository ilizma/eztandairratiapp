package com.ilizma.schedule.view.bind.factory.di

import com.ilizma.schedule.view.bind.factory.ProgramBinderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ProgramBinderFactoryModule {

    @Provides
    fun provideProgramBinderFactory(
    ): ProgramBinderFactory = ProgramBinderFactory()

}