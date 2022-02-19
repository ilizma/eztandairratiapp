package com.ilizma.errormanagement.view.bind.di

import com.ilizma.errormanagement.view.bind.CrashActivityBinder
import com.ilizma.errormanagement.view.bind.CrashActivityBinderImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object CrashActivityBinderModule {

    @Provides
    fun provideCrashActivityBinder(
    ): CrashActivityBinder = CrashActivityBinderImp()

}