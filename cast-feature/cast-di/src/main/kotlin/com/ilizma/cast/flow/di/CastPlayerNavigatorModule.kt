package com.ilizma.cast.flow.di

import android.app.Activity
import com.ilizma.cast.flow.navigator.CastPlayerNavigator
import com.ilizma.cast.flow.navigator.CastPlayerNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object CastPlayerNavigatorModule {

    @Provides
    fun provideCastPlayerNavigator(
        activity: Activity,
    ): CastPlayerNavigator = CastPlayerNavigatorImp(
        activity = activity,
    )

}