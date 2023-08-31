package com.ilizma.player.framework.core.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ServiceComponent::class)
object NotificationManagerCompatModule {

    @Provides
    fun provideNotificationManagerCompat(
        @ApplicationContext context: Context,
    ): NotificationManagerCompat = NotificationManagerCompat.from(context)

}