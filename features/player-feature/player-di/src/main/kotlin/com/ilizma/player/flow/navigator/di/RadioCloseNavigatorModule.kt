package com.ilizma.player.flow.navigator.di

import android.app.Activity
import com.ilizma.player.flow.navigator.RadioCloseNavigator
import com.ilizma.player.flow.navigator.RadioCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object RadioCloseNavigatorModule {

    @Provides
    fun provideRadioCloseNavigator(
        activity: Activity,
    ): RadioCloseNavigator = RadioCloseNavigatorImp(
        activity = activity,
    )

}