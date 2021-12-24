package com.ilizma.player.framework.app.di

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

const val CHANNEL_ID = "com.ilizma.eztanda_channel_id"
const val CHANNEL_NAME = "com.ilizma.eztanda_channel_name"

@Module
@InstallIn(FragmentComponent::class)
object NotificationChannelModule {

    @Provides
    @RequiresApi(Build.VERSION_CODES.O)
    fun provideStartPendingIntent(
        context: Context,
    ): NotificationChannel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_LOW
    )

}