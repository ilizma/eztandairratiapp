package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.FacebookNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object FacebookNavigatorModule {

    @Provides
    fun provideFacebookNavigator(
        @ActivityContext context: Context,
    ): FacebookNavigator = FacebookNavigatorImp(
        context,
    )

}