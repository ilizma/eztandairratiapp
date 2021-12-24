package com.ilizma.player.framework

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.ilizma.player.framework.model.PlayerConnectionState
import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.service.NETWORK_FAILURE
import com.ilizma.player.framework.service.PLAYER_START
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
        }
    }

    override fun stop() {
        when (playerConnectionState.value) {
            PlayerConnectionState.Connected -> mediaController.transportControls.stop()
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
                PLAYER_START -> playerState.onNext(PlayerState.Playing)
                NETWORK_FAILURE -> playerState.onNext(PlayerState.Error)
            }
        }

        override fun onSessionDestroyed() {
            mediaBrowserConnectionCallback.onConnectionSuspended()
            mediaBrowser.disconnect()
        }
    }

}