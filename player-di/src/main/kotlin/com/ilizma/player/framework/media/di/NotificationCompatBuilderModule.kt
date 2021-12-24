package com.ilizma.player.framework.media.di

import android.content.Context
import androidx.core.app.NotificationCompat
import com.ilizma.player.di.R
import com.ilizma.player.framework.app.di.CHANNEL_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NotificationCompatBuilderModule {

    @Provides
    fun provideNotificationCompatBuilder(
        context: Context,
    ): NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .apply {
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setSmallIcon(R.drawable.ic_notification)
        }

}