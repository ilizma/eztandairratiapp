package com.ilizma.player.framework.app.di

import android.app.PendingIntent
import android.content.Context
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.session.MediaButtonReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

const val START_PENDING_INTENT_NAMED = "START_PENDING_INTENT_NAMED"

@Module
@InstallIn(ServiceComponent::class)
object StartPendingIntentModule {

    @Provides
    @Named(START_PENDING_INTENT_NAMED)
    fun provideStartPendingIntent(
        @ApplicationContext context: Context,
    ): PendingIntent = MediaButtonReceiver.buildMediaButtonPendingIntent(
        context,
        PlaybackStateCompat.ACTION_PLAY,
    )

}