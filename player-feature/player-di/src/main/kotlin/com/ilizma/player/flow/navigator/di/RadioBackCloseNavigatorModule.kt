package com.ilizma.player.flow.navigator.di

import android.content.Context
import com.ilizma.player.flow.navigator.RadioBackCloseNavigator
import com.ilizma.player.flow.navigator.RadioBackCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(FragmentComponent::class)
object RadioBackCloseNavigatorModule {

    @Provides
    fun provideRadioBackCloseNavigator(
        @ActivityContext context: Context,
    ): RadioBackCloseNavigator = RadioBackCloseNavigatorImp(
        context = context,
    )

}