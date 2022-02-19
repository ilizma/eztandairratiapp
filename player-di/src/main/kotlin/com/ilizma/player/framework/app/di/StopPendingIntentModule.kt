package com.ilizma.player.framework.app.di

import android.app.PendingIntent
import android.content.Context
import android.support.v4.media.session.PlaybackStateCompat
import androidx.media.session.MediaButtonReceiver
import com.ilizma.player.framework.service.STOP_PENDING_INTENT_NAMED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Named

@Module
@InstallIn(ServiceComponent::class)
object StopPendingIntentModule {

    @Provides
    @Named(STOP_PENDING_INTENT_NAMED)
    fun provideStopPendingIntent(
        @ApplicationContext context: Context,
    ): PendingIntent = MediaButtonReceiver.buildMediaButtonPendingIntent(
        context,
        PlaybackStateCompat.ACTION_STOP,
    )

}