package com.ilizma.player.flow.navigator.di

import com.ilizma.player.flow.navigator.RadioBackCloseNavigator
import com.ilizma.player.flow.navigator.RadioBackCloseNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object RadioBackCloseNavigatorModule {

    @Provides
    fun provideRadioBackCloseNavigator(
    ): RadioBackCloseNavigator = RadioBackCloseNavigatorImp(
    )

}