package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.PhoneNavigator
import com.ilizma.menu.flow.navigator.PhoneNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object PhoneNavigatorModule {

    @Provides
    fun providePhoneNavigator(
        @ActivityContext context: Context,
    ): PhoneNavigator = PhoneNavigatorImp(
        context,
    )

}