package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.InstagramNavigator
import com.ilizma.menu.flow.navigator.InstagramNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object InstagramNavigatorModule {

    @Provides
    fun provideInstagramNavigator(
        @ActivityContext context: Context,
    ): InstagramNavigator = InstagramNavigatorImp(
        context,
    )

}