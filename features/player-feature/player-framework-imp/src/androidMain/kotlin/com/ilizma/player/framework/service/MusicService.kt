package com.ilizma.player.framework.service

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.session.CommandButton
import androidx.media3.session.DefaultMediaNotificationProvider
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSession.ControllerInfo
import androidx.media3.session.MediaSessionService
import com.google.common.collect.ImmutableList
import com.ilizma.player.framework.factory.MediaSessionBuilderFactory
import com.ilizma.player.framework.factory.PlayerFactory
import com.ilizma.resources.R
import org.koin.android.ext.android.inject

@UnstableApi
class MusicService : MediaSessionService(), AudioManager.OnAudioFocusChangeListener {

    private val playerFactory: PlayerFactory<ExoPlayer> by inject()
    private val mediaSessionBuilderFactory: MediaSessionBuilderFactory<MediaSession.Builder, ExoPlayer> by inject()
    private val noisyAudioIntentFilter: IntentFilter by inject()

    private lateinit var player: ExoPlayer
    private lateinit var mediaSession: MediaSession
    private var audioFocusRequest: AudioFocusRequest? = null

    private val mNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            player.stop()
        }
    }

    private inner class MediaSessionCallback : MediaSession.Callback {
        override fun onConnect(
            session: MediaSession,
            controller: ControllerInfo,
        ): MediaSession.ConnectionResult = super.onConnect(session, controller)
            .let {
                it.availableSessionCommands
                    .buildUpon()
                    .build() to it.availablePlayerCommands
            }.let { MediaSession.ConnectionResult.accept(it.first, it.second) }
    }

    private inner class CustomMediaNotificationProvider : MediaNotification.Provider {

        val defaultMediaNotificationProvider = DefaultMediaNotificationProvider(this@MusicService)
        override fun createNotification(
            mediaSession: MediaSession,
            customLayout: ImmutableList<CommandButton>,
            actionFactory: MediaNotification.ActionFactory,
            onNotificationChangedCallback: MediaNotification.Provider.Callback,
        ): MediaNotification = defaultMediaNotificationProvider
            .apply { setSmallIcon(R.drawable.ic_notification) }
            .createNotification(
                mediaSession,
                customLayout,
                actionFactory,
                onNotificationChangedCallback,
            )

        override fun handleCustomCommand(
            session: MediaSession,
            action: String,
            extras: Bundle,
        ): Boolean = defaultMediaNotificationProvider.handleCustomCommand(
            session,
            action,
            extras,
        )

    }

    override fun onCreate() {
        super.onCreate()
        player = playerFactory.create()
            .apply {
                setWakeMode(PowerManager.PARTIAL_WAKE_LOCK)
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .apply { setContentType(C.AUDIO_CONTENT_TYPE_MUSIC) }
                        .build(),
                    true
                )
                CustomMediaNotificationProvider()
                    .let { setMediaNotificationProvider(it) }
                volume = 1.0f
                addAnalyticsListener(EventLogger())
            }
        MediaSessionCallback()
            .let { mediaSessionBuilderFactory.create(player).setCallback(it).build() }
            .let { mediaSession = it }
        initNoisyReceiver()
    }

    override fun onGetSession(
        controllerInfo: ControllerInfo,
    ): MediaSession = mediaSession

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun initNoisyReceiver() {
        // Handles headphones coming unplugged. cannot be done through a manifest receiver
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                registerReceiver(mNoisyReceiver, noisyAudioIntentFilter, RECEIVER_NOT_EXPORTED)

            else -> registerReceiver(mNoisyReceiver, noisyAudioIntentFilter)
        }
    }

    override fun onAudioFocusChange(
        focusChange: Int,
    ) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS,
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT,
                -> player.stop()

            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ->
                player.volume = 0.3f

            AudioManager.AUDIOFOCUS_GAIN -> if (player.isPlaying.not()) {
                player.play()
            } else {
                player.volume = 1.0f
            }
        }
    }

    override fun onDestroy() {
        player.stop()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> stopForeground(STOP_FOREGROUND_REMOVE)
            else -> @Suppress("DEPRECATION")
            stopForeground(true)
        }
        mediaSession.apply {
            player.release()
            release()
        }
        abandonAudioFocus(getSystemService(AUDIO_SERVICE) as AudioManager)
        unregisterReceiver(mNoisyReceiver)
        super.onDestroy()
    }

    private fun abandonAudioFocus(
        audioManager: AudioManager,
    ) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> audioFocusRequest
                ?.let { audioManager.abandonAudioFocusRequest(it) }

            else -> @Suppress("DEPRECATION")
            audioManager.abandonAudioFocus(this)
        }
    }

}