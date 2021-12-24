package com.ilizma.player.framework.core.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NotificationManagerCompatModule {

    @Provides
    fun provideNotificationManagerCompat(
        context: Context,
    ): NotificationManagerCompat = NotificationManagerCompat.from(context)

}