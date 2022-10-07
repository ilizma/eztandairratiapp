package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.TwitterNavigator
import com.ilizma.menu.flow.navigator.TwitterNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
object TwitterNavigatorModule {

    @Provides
    fun provideTwitterNavigator(
        @ApplicationContext context: Context,
    ): TwitterNavigator = TwitterNavigatorImp(
        context,
    )

}