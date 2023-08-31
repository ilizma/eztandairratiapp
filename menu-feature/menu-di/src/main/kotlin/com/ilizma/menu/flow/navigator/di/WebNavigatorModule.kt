package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.WebNavigator
import com.ilizma.menu.flow.navigator.WebNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object WebNavigatorModule {

    @Provides
    fun provideWebNavigator(
        @ActivityContext context: Context,
    ): WebNavigator = WebNavigatorImp(
        context,
    )

}