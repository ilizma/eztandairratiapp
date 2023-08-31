package com.ilizma.player.framework.app.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent

const val CHANNEL_ID = "com.ilizma.eztanda_channel_id"
const val CHANNEL_NAME = "com.ilizma.eztanda_channel_name"

@Module
@InstallIn(ServiceComponent::class)
object NotificationChannelModule {

    @Provides
    @RequiresApi(Build.VERSION_CODES.O)
    fun provideNotificationChannel(
    ): NotificationChannel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_LOW
    )

}