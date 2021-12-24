package com.ilizma.player.framework.service.di

import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.IntentFilter
import android.media.AudioFocusRequest
import android.media.MediaPlayer
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media.MediaBrowserServiceCompat
import com.ilizma.player.framework.app.di.STOP_PENDING_INTENT_NAMED
import com.ilizma.player.framework.service.MusicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named
import javax.inject.Singleton
import androidx.media.app.NotificationCompat as MediaNotificationCompat

@Module
@InstallIn(FragmentComponent::class)
object MusicServiceModule {

    @Provides
    @Singleton
    fun provideMusicService(
        mediaPlayer: MediaPlayer,
        mediaSession: MediaSessionCompat,
        audioFocusRequestBuilder: AudioFocusRequest.Builder,
        playbackStateBuilder: PlaybackStateCompat.Builder,
        mediaStyle: MediaNotificationCompat.MediaStyle,
        notificationBuilder: NotificationCompat.Builder,
        @Named(STOP_PENDING_INTENT_NAMED) stopPendingIntent: PendingIntent,
        notificationManagerCompat: NotificationManagerCompat,
        notificationChannelLow: NotificationChannel,
        playAction: NotificationCompat.Action,
        stopAction: NotificationCompat.Action,
        browseRoot: MediaBrowserServiceCompat.BrowserRoot,
        noisyAudioIntentFilter: IntentFilter,
    ): MusicService = MusicService(
        mediaPlayer = mediaPlayer,
        mediaSession = mediaSession,
        audioFocusRequestBuilder = audioFocusRequestBuilder,
        playbackStateBuilder = playbackStateBuilder,
        mediaStyle = mediaStyle,
        notificationBuilder = notificationBuilder,
        stopPendingIntent = stopPendingIntent,
        notificationManagerCompat = notificationManagerCompat,
        notificationChannelLow = notificationChannelLow,
        playAction = playAction,
        stopAction = stopAction,
        browseRoot = browseRoot,
        noisyAudioIntentFilter = noisyAudioIntentFilter,
    )

}