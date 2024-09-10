package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.TwitterNavigator
import com.ilizma.menu.flow.navigator.TwitterNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object TwitterNavigatorModule {

    @Provides
    fun provideTwitterNavigator(
        @ActivityContext context: Context,
    ): TwitterNavigator = TwitterNavigatorImp(
        context,
    )

}