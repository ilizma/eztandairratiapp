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
import com.ilizma.player.framework.model.PlayerEvent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named
import androidx.media.app.NotificationCompat as MediaNotificationCompat

const val STOP_PENDING_INTENT_NAMED = "STOP_PENDING_INTENT_NAMED"
const val NOTIFICATION_COMPAT_PLAY_ACTION_NAMED = "NOTIFICATION_COMPAT_PLAY_ACTION_NAMED"
const val NOTIFICATION_COMPAT_STOP_ACTION_NAMED = "NOTIFICATION_COMPAT_STOP_ACTION_NAMED"
private const val NOTIFICATION_ID = 1076

@AndroidEntryPoint
class MusicService : MediaBrowserServiceCompat(), AudioManager.OnAudioFocusChangeListener {

    @Inject
    lateinit var mediaPlayer: MediaPlayer

    @Inject
    lateinit var mediaSession: MediaSessionCompat

    @Inject
    lateinit var audioFocusRequestBuilder: AudioFocusRequest.Builder

    @Inject
    lateinit var playbackStateBuilder: PlaybackStateCompat.Builder

    @Inject
    lateinit var mediaStyle: MediaNotificationCompat.MediaStyle

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    @Inject
    @Named(STOP_PENDING_INTENT_NAMED)
    lateinit var stopPendingIntent: PendingIntent

    @Inject
    lateinit var notificationManagerCompat: NotificationManagerCompat

    @Inject
    lateinit var notificationChannelLow: NotificationChannel

    @Inject
    @Named(NOTIFICATION_COMPAT_PLAY_ACTION_NAMED)
    lateinit var playAction: NotificationCompat.Action

    @Inject
    @Named(NOTIFICATION_COMPAT_STOP_ACTION_NAMED)
    lateinit var stopAction: NotificationCompat.Action

    @Inject
    lateinit var browseRoot: BrowserRoot

    @Inject
    lateinit var noisyAudioIntentFilter: IntentFilter

    private var audioFocusRequest: AudioFocusRequest? = null

    private val mNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            mediaSessionCallback.onStop()
        }
    }

    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {

        override fun onPlay() {
            super.onPlay()
            if (successfullyRetrievedAudioFocus().not()) return

            initMediaPlayer()

            mediaSession.isActive = true
            setMediaPlaybackState(PlaybackStateCompat.STATE_PLAYING)
            showLoadingNotification()
        }

        override fun onStop() {
            super.onStop()
            mediaPlayer.stop()
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
            notificationBuilder.clearActions()

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
            notificationBuilder.clearActions()
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
                mediaSession.sendSessionEvent(PlayerEvent.START.name, null)
            }
            setOnErrorListener { _, what, _ ->
                when (what) {
                    MediaPlayer.MEDIA_ERROR_IO -> PlayerEvent.IO_FAILURE
                    MediaPlayer.MEDIA_ERROR_MALFORMED -> PlayerEvent.MALFORMED_FAILURE
                    MediaPlayer.MEDIA_ERROR_UNSUPPORTED -> PlayerEvent.UNSUPPORTED_FAILURE
                    MediaPlayer.MEDIA_ERROR_TIMED_OUT -> PlayerEvent.TIMEOUT_FAILURE
                    MediaPlayer.MEDIA_ERROR_SERVER_DIED -> PlayerEvent.NETWORK_FAILURE
                    MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK -> PlayerEvent.PROGRESSIVE_PLAYBACK_NOT_VALID_FAILURE
                    MediaPlayer.MEDIA_ERROR_UNKNOWN -> PlayerEvent.UNKNOWN_FAILURE
                    else -> PlayerEvent.UNKNOWN_FAILURE
                }.let { mediaSession.sendSessionEvent(it.name, null) }
                mediaSessionCallback.onStop()
                true
            }
            prepareAsync()
        }
    }

    private fun showPlayingNotification() {
        notificationBuilder.clearActions()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManagerCompat.createNotificationChannel(notificationChannelLow)
        }

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
        audioManager: AudioManager,
    ) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            @Suppress("DEPRECATION")
            audioManager.abandonAudioFocus(this)
        } else {
            audioFocusRequest
                ?.let { audioManager.abandonAudioFocusRequest(it) }
        }
    }

}