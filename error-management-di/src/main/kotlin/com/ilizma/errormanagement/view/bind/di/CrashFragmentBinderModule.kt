package com.ilizma.errormanagement.view.bind.di

import com.ilizma.errormanagement.view.bind.CrashFragmentBinder
import com.ilizma.errormanagement.view.bind.CrashFragmentBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object CrashFragmentBinderModule {

    @Provides
    fun provideCrashFragmentBinder(
    ): CrashFragmentBinder = CrashFragmentBinderImp()

}