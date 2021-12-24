package com.ilizma.player.framework.media.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import androidx.media.app.NotificationCompat

@Module
@InstallIn(FragmentComponent::class)
object MediaNotificationCompatMediaStyleModule {

    @Provides
    fun provideMediaNotificationCompatMediaStyle(
    ): NotificationCompat.MediaStyle = NotificationCompat.MediaStyle()

}