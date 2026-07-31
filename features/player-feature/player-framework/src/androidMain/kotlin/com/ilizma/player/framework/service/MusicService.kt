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
import androidx.core.content.ContextCompat
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import androidx.media3.session.CommandButton
import androidx.media3.session.DefaultMediaNotificationProvider
import androidx.media3.session.LibraryResult
import androidx.media3.session.MediaNotification
import androidx.media3.session.MediaNotification.Provider.NotificationChannelInfo
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSession.ControllerInfo
import androidx.media3.session.SessionError
import com.google.common.collect.ImmutableList
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import com.ilizma.player.framework.factory.PlayerFactory
import com.ilizma.player.framework.BuildKonfig
import com.ilizma.player.framework.getArtworkData
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.model.WidgetAction
import com.ilizma.player.framework.updater.PlayerWidgetUpdater
import com.ilizma.resources.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

const val WIDGET_ACTION = "widget_action"

@UnstableApi
class MusicService : MediaLibraryService(), AudioManager.OnAudioFocusChangeListener {

    private val playerFactory: PlayerFactory<ExoPlayer> by inject()
    private val noisyAudioIntentFilter: IntentFilter by inject()
    private val playerWidgetUpdater: PlayerWidgetUpdater by inject()

    private lateinit var player: ExoPlayer
    private lateinit var mediaLibrarySession: MediaLibrarySession
    private var audioFocusRequest: AudioFocusRequest? = null

    companion object {
        private const val ROOT_ID = "[rootID]"
        private const val LIVE_ID = "eztanda_radio_stream"
    }

    private val mediaItem by lazy {
        MediaItem.Builder()
            .setMediaId(LIVE_ID)
            .setUri(BuildKonfig.AUDIO_URL)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(getString(R.string.radio_name))
                    .setArtist(getString(R.string.free_radio))
                    .setDisplayTitle(getString(R.string.radio_name))
                    .setSubtitle(getString(R.string.free_radio))
                    .setArtworkData(
                        getArtworkData(this@MusicService, R.drawable.img_splash),
                        MediaMetadata.PICTURE_TYPE_FRONT_COVER
                    )
                    .setIsPlayable(true)
                    .setIsBrowsable(false)
                    .build()
            )
            .build()
    }

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val mNoisyReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            player.stop()
        }
    }

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            if (isPlaying) {
                onMetadataChanged(PlayerState.Playing)
            } else {
                if (player.playbackState != Player.STATE_BUFFERING && player.playerError == null) {
                    onMetadataChanged(PlayerState.Stopped)
                }
            }
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
            when (playbackState) {
                Player.STATE_BUFFERING -> onMetadataChanged(PlayerState.Loading)
                Player.STATE_READY -> if (player.playWhenReady) {
                    PlayerState.Playing
                } else {
                    PlayerState.Stopped
                }.let { onMetadataChanged(it) }

                Player.STATE_ENDED,
                Player.STATE_IDLE,
                    -> onMetadataChanged(PlayerState.Stopped)
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            // TODO map PlaybackException.errorCode to more specific PlayerState.Error types
            onMetadataChanged(PlayerState.Error.GenericError)
        }
    }

    private inner class MediaLibrarySessionCallback : MediaLibrarySession.Callback {
        override fun onConnect(
            session: MediaSession,
            controller: ControllerInfo,
        ): MediaSession.ConnectionResult = super.onConnect(session, controller)
            .let {
                it.availableSessionCommands
                    .buildUpon()
                    .build() to it.availablePlayerCommands
            }.let { MediaSession.ConnectionResult.accept(it.first, it.second) }

        override fun onGetLibraryRoot(
            session: MediaLibrarySession,
            browser: ControllerInfo,
            params: LibraryParams?,
        ): ListenableFuture<LibraryResult<MediaItem>> {
            val rootItem = MediaItem.Builder()
                .setMediaId(ROOT_ID)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setIsBrowsable(true)
                        .setIsPlayable(false)
                        .setMediaType(MediaMetadata.MEDIA_TYPE_FOLDER_MIXED)
                        .setTitle("Eztanda Irratia")
                        .build()
                )
                .build()
            return Futures.immediateFuture(LibraryResult.ofItem(rootItem, params))
        }

        override fun onGetChildren(
            session: MediaLibrarySession,
            browser: ControllerInfo,
            parentId: String,
            page: Int,
            pageSize: Int,
            params: LibraryParams?,
        ): ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> {
            return if (parentId == ROOT_ID) {
                val mediaItem = session.player.currentMediaItem ?: this@MusicService.mediaItem
                Futures.immediateFuture(
                    LibraryResult.ofItemList(
                        ImmutableList.of(
                            MediaItem.Builder()
                                .setMediaId(LIVE_ID)
                                .setMediaMetadata(
                                    mediaItem.mediaMetadata.buildUpon()
                                        .setIsBrowsable(false)
                                        .setIsPlayable(true)
                                        .build()
                                )
                                .setUri(mediaItem.requestMetadata.mediaUri ?: mediaItem.localConfiguration?.uri)
                                .build()
                        ),
                        params
                    )
                )
            } else {
                Futures.immediateFuture(LibraryResult.ofError(SessionError.ERROR_BAD_VALUE))
            }
        }

        override fun onGetItem(
            session: MediaLibrarySession,
            browser: ControllerInfo,
            mediaId: String,
        ): ListenableFuture<LibraryResult<MediaItem>> {
            val item = when (mediaId) {
                LIVE_ID -> this@MusicService.mediaItem.buildUpon()
                    .setMediaMetadata(
                        this@MusicService.mediaItem.mediaMetadata.buildUpon()
                            .setIsBrowsable(false)
                            .setIsPlayable(true)
                            .build()
                    )
                    .build()

                ROOT_ID -> MediaItem.Builder()
                    .setMediaId(ROOT_ID)
                    .setMediaMetadata(
                        MediaMetadata.Builder()
                            .setIsBrowsable(true)
                            .setIsPlayable(false)
                            .setMediaType(MediaMetadata.MEDIA_TYPE_FOLDER_MIXED)
                            .setTitle(getString(R.string.radio_name))
                            .build()
                    )
                    .build()

                else -> return Futures.immediateFuture(LibraryResult.ofError(SessionError.ERROR_BAD_VALUE))
            }
            return Futures.immediateFuture(LibraryResult.ofItem(item, null))
        }
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
            ).apply {
                notification.color = ContextCompat.getColor(this@MusicService, R.color.purple)
            }

        override fun handleCustomCommand(
            session: MediaSession,
            action: String,
            extras: Bundle,
        ): Boolean = defaultMediaNotificationProvider.handleCustomCommand(
            session,
            action,
            extras,
        )

        override fun getNotificationChannelInfo(
        ): NotificationChannelInfo = NotificationChannelInfo(
            "eztanda_playback_channel",
            getString(R.string.music_notification_channel_name),
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
                addListener(playerListener)
                addAnalyticsListener(EventLogger())
            }
        MediaLibrarySession.Builder(this, player, MediaLibrarySessionCallback())
            .build()
            .let { mediaLibrarySession = it }
        initNoisyReceiver()
    }

    override fun onGetSession(
        controllerInfo: ControllerInfo,
    ): MediaLibrarySession = mediaLibrarySession

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        val action = intent?.getStringExtra(WIDGET_ACTION)
        if (action != null) {
            handleWidgetAction(action)
            return START_STICKY
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun handleWidgetAction(action: String) {
        try {
            when (WidgetAction.valueOf(action)) {
                WidgetAction.PLAY -> {
                    if (player.currentMediaItem == null) {
                        player.setMediaItem(mediaItem)
                    }
                    player.prepare()
                    player.play()
                }

                WidgetAction.STOP -> player.stop()
            }
        } catch (e: Exception) {
            e.printStackTrace()
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
                if (player.currentMediaItem == null) {
                    player.setMediaItem(mediaItem)
                    player.prepare()
                }
                player.play()
            } else {
                player.volume = 1.0f
            }
        }
    }

    override fun onDestroy() {
        player.removeListener(playerListener)
        player.stop()
        serviceScope.cancel()
        mediaLibrarySession.apply {
            player.release()
            release()
        }
        abandonAudioFocus(getSystemService(AUDIO_SERVICE) as AudioManager)
        unregisterReceiver(mNoisyReceiver)
        stopForeground(STOP_FOREGROUND_REMOVE)
        super.onDestroy()
    }

    fun onMetadataChanged(
        playerState: PlayerState,
    ) {
        serviceScope.launch {
            playerWidgetUpdater.updateMediaWidget(
                context = applicationContext,
                playerState = playerState,
            )
        }
    }

    private fun updateMediaWidget(
        context: Context,
        playerState: PlayerState,
    ) {
        serviceScope.launch {
            playerWidgetUpdater.updateMediaWidget(
                context = context,
                playerState = playerState,
            )
        }
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    private fun initNoisyReceiver() {
        // Handles headphones coming unplugged. cannot be done through a manifest receiver
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                registerReceiver(mNoisyReceiver, noisyAudioIntentFilter, RECEIVER_NOT_EXPORTED)

            else -> registerReceiver(mNoisyReceiver, noisyAudioIntentFilter)
        }
    }

    private fun abandonAudioFocus(
        audioManager: AudioManager,
    ) {
        audioFocusRequest
            ?.let { audioManager.abandonAudioFocusRequest(it) }
    }

}
