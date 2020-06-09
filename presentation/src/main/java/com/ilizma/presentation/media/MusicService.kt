package com.ilizma.presentation.media

import android.app.Notification
import android.app.PendingIntent
import android.content.*
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.media.MediaBrowserServiceCompat
import androidx.media.session.MediaButtonReceiver
import com.ilizma.presentation.BuildConfig
import com.ilizma.presentation.R
import com.ilizma.presentation.media.NotificationHelper.from
import com.ilizma.presentation.ui.content.MainActivity

const val NETWORK_FAILURE = "com.ilizma.presentation.media.NETWORK_FAILURE"
const val PLAYER_START = "com.ilizma.presentation.media.PLAYER_START"
private const val NOTIFICATION_ID = 1076
private const val TAG = "MusicService"

class MusicService : MediaBrowserServiceCompat(), AudioManager.OnAudioFocusChangeListener {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaSession: MediaSessionCompat
    private lateinit var audioFocusRequest: AudioFocusRequest

    private val mNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            mediaSessionCallback.onStop()
        }
    }

    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {

        override fun onPlay() {
            super.onPlay()
            if (!successfullyRetrievedAudioFocus()) return

            mediaSession.isActive = true
            setMediaPlaybackState(PlaybackStateCompat.STATE_PLAYING)
            showLoadingNotification()
        }

        override fun onStop() {
            super.onStop()
            if (this@MusicService::mediaPlayer.isInitialized) mediaPlayer.run {
                stop()
                release()
            }
            setMediaPlaybackState(PlaybackStateCompat.STATE_STOPPED)
            showStopNotification()
        }

        private fun setMediaPlaybackState(state: Int) {
            val playbackStateBuilder = PlaybackStateCompat.Builder().apply {
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
            val builder = from(this@MusicService, mediaSession) ?: return
            builder.setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().run {
                    setMediaSession(mediaSession.sessionToken)
                }
            )
            startForeground(NOTIFICATION_ID, builder.build().apply {
                // don't hide the notification
                flags = Notification.FLAG_ONGOING_EVENT
            })
        }

        private fun showStopNotification() {
            val builder = from(this@MusicService, mediaSession) ?: return
            builder.addAction(
                NotificationCompat.Action(
                    R.drawable.ic_play,
                    getString(R.string.play),
                    MediaButtonReceiver.buildMediaButtonPendingIntent(
                        this@MusicService,
                        PlaybackStateCompat.ACTION_PLAY
                    )
                )
            )
            builder.setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().run {
                    setShowActionsInCompactView(0)
                    setMediaSession(mediaSession.sessionToken)
                }
            )
            NotificationManagerCompat.from(this@MusicService).run {
                notify(NOTIFICATION_ID, builder.build())
            }
            stopForeground(false)
        }
    }

    override fun onCreate() {
        super.onCreate()
        initMediaSession()
        initNoisyReceiver()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        MediaButtonReceiver.handleIntent(mediaSession, intent)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? = BrowserRoot(getString(R.string.app_name), null)

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        with(mediaPlayer) {
            setDataSource(BuildConfig.AUDIO_URL)
            setWakeMode(this@MusicService, PowerManager.PARTIAL_WAKE_LOCK)
            setAudioAttributes(
                AudioAttributes.Builder().run {
                    setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    build()
                }
            )
            setOnPreparedListener {
                it.start()
                showPlayingNotification()
                mediaSession.sendSessionEvent(PLAYER_START, null)
            }
            setOnErrorListener { _, what, _ ->
                when (what) {
                    MediaPlayer.MEDIA_ERROR_SERVER_DIED,
                    MediaPlayer.MEDIA_ERROR_UNKNOWN -> {
                        mediaSession.sendSessionEvent(NETWORK_FAILURE, null)
                        mediaSessionCallback.onStop()
                    }
                }
                true
            }
            isLooping = false
            setVolume(1.0f, 1.0f)
            prepareAsync()
        }
    }

    private fun showPlayingNotification() {
        val builder = from(this@MusicService, mediaSession) ?: return
        builder.addAction(
            NotificationCompat.Action(
                R.drawable.ic_stop,
                getString(R.string.stop),
                MediaButtonReceiver.buildMediaButtonPendingIntent(
                    this@MusicService,
                    PlaybackStateCompat.ACTION_STOP
                )
            )
        )
        builder.setStyle(
            androidx.media.app.NotificationCompat.MediaStyle().run {
                setShowActionsInCompactView(0)
                setMediaSession(mediaSession.sessionToken)
            }
        )
        startForeground(NOTIFICATION_ID, builder.build().apply {
            // don't hide the notification
            flags = Notification.FLAG_ONGOING_EVENT
        })
    }

    private fun initMediaSession() {
        val mediaButtonReceiver = ComponentName(this, MediaButtonReceiver::class.java)
        val mediaButtonIntent = Intent(Intent.ACTION_MEDIA_BUTTON).apply {
            setClass(this@MusicService, MediaButtonReceiver::class.java)
        }
        val pendingIntent = PendingIntent.getBroadcast(this@MusicService, 0, mediaButtonIntent, 0)
        val resultIntent = Intent(this@MusicService, MainActivity::class.java)
        val pIntent = PendingIntent.getActivity(this@MusicService, 0, resultIntent, 0)

        mediaSession = MediaSessionCompat(this, TAG, mediaButtonReceiver, null).apply {
            setCallback(mediaSessionCallback)
            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
            setMediaButtonReceiver(pendingIntent)
            setSessionActivity(pIntent)
            val bitmap = getDrawable(R.drawable.img_eztanda)?.toBitmap()
            setMetadata(
                MediaMetadataCompat.Builder().run {
                    putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE,
                        getString(R.string.radio_name)
                    )
                    putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE,
                        getString(R.string.free_radio)
                    )
                    putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION,
                        getString(R.string.listening)
                    )
                    putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap)
                    build()
                }
            )
            bitmap?.recycle()
            this@MusicService.sessionToken = sessionToken
        }
    }

    private fun initNoisyReceiver() {
        //Handles headphones coming unplugged. cannot be done through a manifest receiver
        registerReceiver(mNoisyReceiver, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
    }

    @Suppress("DEPRECATION")
    private fun successfullyRetrievedAudioFocus(): Boolean {
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val result = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            audioManager.requestAudioFocus(
                this,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN
            )
        } else {
            audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).run {
                setAudioAttributes(AudioAttributes.Builder().run {
                    setUsage(AudioAttributes.USAGE_MEDIA)
                    setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    build()
                })
                setAcceptsDelayedFocusGain(true)
                setOnAudioFocusChangeListener(this@MusicService)
                build()
            }

            audioManager.requestAudioFocus(audioFocusRequest)
        }
        return (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED).apply {
            if (this) initMediaPlayer()
        }
    }

    override fun onAudioFocusChange(focusChange: Int) {
        when (focusChange) {
            AudioManager.AUDIOFOCUS_LOSS,
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> mediaSessionCallback.onStop()
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ->
                mediaPlayer.setVolume(0.3f, 0.3f)
            AudioManager.AUDIOFOCUS_GAIN -> if (mediaPlayer.isPlaying.not()) {
                initMediaPlayer()
            } else {
                mediaPlayer.setVolume(1.0f, 1.0f)
            }
        }
    }

    // swiping the activity away from recents
    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        destroy()
    }

    @Suppress("DEPRECATION")
    override fun onDestroy() {
        destroy()
        super.onDestroy()
    }

    private fun destroy() {
        mediaSessionCallback.onStop()
        NotificationManagerCompat.from(this).run { cancel(NOTIFICATION_ID) }
        stopForeground(true)
        mediaSession.run {
            isActive = false
            release()
        }
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            audioManager.abandonAudioFocus(this)
        } else {
            audioManager.abandonAudioFocusRequest(audioFocusRequest)
        }
        unregisterReceiver(mNoisyReceiver)
    }

}