package com.ilizma.player.framework

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.ilizma.player.framework.model.PlayerConnectionState
import com.ilizma.player.framework.model.PlayerEvent
import com.ilizma.player.framework.model.PlayerState
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class PlayerFrameworkImp(
    context: Context,
    serviceComponent: ComponentName,
    private val playerState: BehaviorSubject<PlayerState>,
    private val playerConnectionState: BehaviorSubject<PlayerConnectionState>,
) : PlayerFramework {

    override fun getState(
    ): Observable<PlayerState> = playerState

    override fun play() {
        when (playerConnectionState.value) {
            PlayerConnectionState.Connected -> mediaController.transportControls.play()
            PlayerConnectionState.Disconnected -> playerState.onNext(PlayerState.Error)
        }
    }

    override fun stop() {
        when (playerConnectionState.value) {
            PlayerConnectionState.Connected -> mediaController.transportControls.stop()
            PlayerConnectionState.Disconnected -> playerState.onNext(PlayerState.Error)
        }
    }

    private lateinit var mediaController: MediaControllerCompat

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback(context)
    private val mediaBrowser = MediaBrowserCompat(
        context,
        serviceComponent,
        mediaBrowserConnectionCallback,
        null,
    ).apply { connect() }

    private inner class MediaBrowserConnectionCallback(
        private val context: Context,
    ) : MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            mediaController = MediaControllerCompat(context, mediaBrowser.sessionToken)
                .apply { registerCallback(MediaControllerCallback()) }
            playerConnectionState.onNext(PlayerConnectionState.Connected)
        }

        override fun onConnectionSuspended() {
            playerConnectionState.onNext(PlayerConnectionState.Disconnected)
        }

        override fun onConnectionFailed() {
            playerConnectionState.onNext(PlayerConnectionState.Disconnected)
        }
    }

    private inner class MediaControllerCallback : MediaControllerCompat.Callback() {

        override fun onPlaybackStateChanged(
            state: PlaybackStateCompat,
        ) {
            super.onPlaybackStateChanged(state)
            when (state.state) {
                PlaybackStateCompat.ACTION_STOP.toInt() -> playerState.onNext(PlayerState.Stopped)
                PlaybackStateCompat.ACTION_PLAY.toInt(),
                PlaybackStateCompat.STATE_PLAYING -> if (playerState.value != PlayerState.Playing)
                    playerState.onNext(PlayerState.Loading)
            }
        }

        override fun onSessionEvent(
            event: String?,
            extras: Bundle?
        ) {
            super.onSessionEvent(event, extras)
            when (event) {
                PlayerEvent.START.name -> playerState.onNext(PlayerState.Playing)
                // TODO: 24/12/21 Manage errors differentiating them
                PlayerEvent.IO_FAILURE.name -> playerState.onNext(PlayerState.Error)
                PlayerEvent.MALFORMED_FAILURE.name -> playerState.onNext(PlayerState.Error)
                PlayerEvent.UNSUPPORTED_FAILURE.name -> playerState.onNext(PlayerState.Error)
                PlayerEvent.TIMEOUT_FAILURE.name -> playerState.onNext(PlayerState.Error)
                PlayerEvent.NETWORK_FAILURE.name -> playerState.onNext(PlayerState.Error)
                PlayerEvent.PROGRESSIVE_PLAYBACK_NOT_VALID_FAILURE.name -> playerState.onNext(PlayerState.Error)
                PlayerEvent.UNKNOWN_FAILURE.name -> playerState.onNext(PlayerState.Error)
            }
        }

        override fun onSessionDestroyed() {
            mediaBrowserConnectionCallback.onConnectionSuspended()
            mediaBrowser.disconnect()
        }
    }

}