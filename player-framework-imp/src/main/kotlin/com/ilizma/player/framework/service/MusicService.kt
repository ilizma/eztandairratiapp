package com.ilizma.player.framework.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media.MediaBrowserServiceCompat
import androidx.media.session.MediaButtonReceiver
import androidx.media.app.NotificationCompat as MediaNotificationCompat

const val NETWORK_FAILURE = "com.ilizma.player.framework.service.NETWORK_FAILURE"
const val PLAYER_START = "com.ilizma.player.framework.service.PLAYER_START"
private const val NOTIFICATION_ID = 1076

class MusicService(
    private val mediaPlayer: MediaPlayer,
    private val mediaSession: MediaSessionCompat,
    private val audioFocusRequestBuilder: AudioFocusRequest.Builder,
    private val playbackStateBuilder: PlaybackStateCompat.Builder,
    private val mediaStyle: MediaNotificationCompat.MediaStyle,
    private val notificationBuilder: NotificationCompat.Builder,
    private val stopPendingIntent: PendingIntent,
    private val notificationManagerCompat: NotificationManagerCompat,
    private val notificationChannelLow: NotificationChannel,
    private val playAction: NotificationCompat.Action,
    private val stopAction: NotificationCompat.Action,
    private val browseRoot: BrowserRoot,
    private val noisyAudioIntentFilter: IntentFilter,
) : MediaBrowserServiceCompat(), AudioManager.OnAudioFocusChangeListener {

    private lateinit var audioFocusRequest: AudioFocusRequest

    private val mNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            mediaSessionCallback.onStop()
        }
    }

    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {

        override fun onPlay() {
            super.onPlay()
            if (successfullyRetrievedAudioFocus().not()) return

            mediaSession.isActive = true
            setMediaPlaybackState(PlaybackStateCompat.STATE_PLAYING)
            showLoadingNotification()
        }

        override fun onStop() {
            super.onStop()
            mediaPlayer.apply {
                stop()
                release()
            }
            setMediaPlaybackState(PlaybackStateCompat.STATE_STOPPED)
            showStopNotification()
        }

        private fun setMediaPlaybackState(state: Int) {
            playbackStateBuilder.apply {
                if (state == PlaybackStateCompat.STATE_PLAYING) {
                    setActions(PlaybackStateCompat.ACTION_STOP)
                } else {
                    setActions(PlaybackStateCompat.ACTION_PLAY)
                }
                setState(state, PlaybackStateCompat.PLAYBACK_POSITION_UNKNOWN, 0f)
            }
            mediaSession.setPlaybackState(playbackStateBuilder.build())
        }

        private fun showLoadingNotification() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManagerCompat.createNotificationChannel(notificationChannelLow)
            }

            val controller = mediaSession.controller
            val description = controller.metadata.description
            notificationBuilder.apply {
                setContentTitle(description.title)
                setContentText(description.subtitle)
                setSubText(description.description)
                setLargeIcon(description.iconBitmap)
                setContentIntent(controller.sessionActivity)
                mediaStyle.setMediaSession(mediaSession.sessionToken)
                    .let { setStyle(it) }
                setDeleteIntent(stopPendingIntent)
            }

            notificationBuilder.build()
                .apply { flags = Notification.FLAG_ONGOING_EVENT }
                .let { startForeground(NOTIFICATION_ID, it) }
        }

        private fun showStopNotification() {
            notificationBuilder.addAction(playAction)

            mediaStyle.let {
                it.setShowActionsInCompactView(0)
                it.setMediaSession(mediaSession.sessionToken)
            }.let { notificationBuilder.setStyle(it) }
            notificationManagerCompat
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        createNotificationChannel(notificationChannelLow)
                    }
                    notify(NOTIFICATION_ID, notificationBuilder.build())
                }
            stopForeground(false)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mediaSession.setCallback(mediaSessionCallback)
        sessionToken = mediaSession.sessionToken
        initNoisyReceiver()
    }

    override fun onStartCommand(
        intent: Intent,
        flags: Int,
        startId: Int,
    ): Int = MediaButtonReceiver.handleIntent(mediaSession, intent)
        .let { super.onStartCommand(intent, flags, startId) }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot = browseRoot

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>,
    ) { /* no-op */ }

    private fun initMediaPlayer() {
        with(mediaPlayer) {
            setVolume(1.0f, 1.0f)
            setOnPreparedListener {
                it.start()
                showPlayingNotification()
                mediaSession.sendSessionEvent(PLAYER_START, null)
            }
            setOnErrorListener { _, what, _ ->
                when (what) {
                    MediaPlayer.MEDIA_ERROR_IO,
                    MediaPlayer.MEDIA_ERROR_MALFORMED,
                    MediaPlayer.MEDIA_ERROR_UNSUPPORTED,
                    MediaPlayer.MEDIA_ERROR_TIMED_OUT,
                    MediaPlayer.MEDIA_ERROR_SERVER_DIED,
                    MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK,
                    MediaPlayer.MEDIA_ERROR_UNKNOWN -> {
                        mediaSession.sendSessionEvent(NETWORK_FAILURE, null)
                        mediaSessionCallback.onStop()
                    }
                }
                true
            }
            prepareAsync()
        }
    }

    private fun showPlayingNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationManagerCompat
                .apply { createNotificationChannel(notificationChannelLow) }

        notificationBuilder.addAction(stopAction)

        mediaStyle.let {
            it.setShowActionsInCompactView(0)
            it.setMediaSession(mediaSession.sessionToken)
        }.let { notificationBuilder.setStyle(it) }

        notificationBuilder.build()
            .apply { flags = Notification.FLAG_ONGOING_EVENT }
            .let { startForeground(NOTIFICATION_ID, it) }
    }

    private fun initNoisyReceiver() {
        // Handles headphones coming unplugged. cannot be done through a manifest receiver
        registerReceiver(mNoisyReceiver, noisyAudioIntentFilter)
    }

    private fun successfullyRetrievedAudioFocus(
    ): Boolean = requestAudioFocus(getSystemService(Context.AUDIO_SERVICE) as AudioManager)
        .let { (it == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) }
        .also { if (it) initMediaPlayer() }

    private fun requestAudioFocus(
        audioManager: AudioManager,
    ): Int = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        @Suppress("DEPRECATION")
        audioManager.requestAudioFocus(
            this,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN,
        )
    } else {
        audioFocusRequestBuilder.setOnAudioFocusChangeListener(this@MusicService)
        audioFocusRequestBuilder.build()
            .also { audioFocusRequest = it }
            .let { audioManager.requestAudioFocus(it) }
    }

    override fun onAudioFocusChange(
        focusChange: Int,
    ) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS,
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> mediaSessionCallback.onStop()
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ->
                mediaPlayer.setVolume(0.3f, 0.3f)
            AudioManager.AUDIOFOCUS_GAIN -> if (mediaPlayer.isPlaying.not()) {
                mediaSessionCallback.onPlay()
            } else {
                mediaPlayer.setVolume(1.0f, 1.0f)
            }
        }
    }

    override fun onDestroy() {
        mediaSessionCallback.onStop()
        notificationManagerCompat.cancel(NOTIFICATION_ID)
        stopForeground(true)
        mediaSession.apply {
            isActive = false
            release()
        }
        abandonAudioFocus(getSystemService(Context.AUDIO_SERVICE) as AudioManager)
        unregisterReceiver(mNoisyReceiver)
        super.onDestroy()
    }

    private fun abandonAudioFocus(
        audioManager: AudioManager
    ): Int = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        @Suppress("DEPRECATION")
        audioManager.abandonAudioFocus(this)
    } else {
        audioManager.abandonAudioFocusRequest(audioFocusRequest)
    }

}