package com.ilizma.player.framework.app.di

import android.app.PendingIntent
import android.content.Context
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.session.MediaButtonReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

const val STOP_PENDING_INTENT_NAMED = "STOP_PENDING_INTENT_NAMED"

@Module
@InstallIn(FragmentComponent::class)
object StopPendingIntentModule {

    @Provides
    @Named(STOP_PENDING_INTENT_NAMED)
    fun provideStopPendingIntent(
        context: Context,
    ): PendingIntent = MediaButtonReceiver.buildMediaButtonPendingIntent(
        context,
        PlaybackStateCompat.ACTION_STOP
    )

}