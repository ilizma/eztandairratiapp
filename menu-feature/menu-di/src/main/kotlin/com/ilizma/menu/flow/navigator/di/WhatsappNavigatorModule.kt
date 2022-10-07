package com.ilizma.menu.flow.navigator.di

import android.content.Context
import com.ilizma.menu.flow.navigator.WhatsappNavigator
import com.ilizma.menu.flow.navigator.WhatsappNavigatorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(FragmentComponent::class)
object WhatsappNavigatorModule {

    @Provides
    fun provideWhatsappNavigator(
        @ActivityContext context: Context,
    ): WhatsappNavigator = WhatsappNavigatorImp(
        context,
    )

}