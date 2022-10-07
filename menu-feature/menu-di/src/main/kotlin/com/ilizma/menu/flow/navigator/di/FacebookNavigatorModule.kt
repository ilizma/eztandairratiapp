package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.FacebookNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(FragmentComponent::class)
object FacebookNavigatorModule {

    @Provides
    fun provideFacebookNavigator(
        @ApplicationContext context: Context,
    ): FacebookNavigator = FacebookNavigatorImp(
        context,
    )

}