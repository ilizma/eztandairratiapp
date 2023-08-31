package com.ilizma.player.framework.media.di

import androidx.media.app.NotificationCompat
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

@Module
@InstallIn(ServiceComponent::class)
object MediaNotificationCompatMediaStyleModule {

    @Provides
    fun provideMediaNotificationCompatMediaStyle(
    ): NotificationCompat.MediaStyle = NotificationCompat.MediaStyle()
        .also { it.setShowActionsInCompactView(0) }

}