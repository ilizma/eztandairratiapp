package com.ilizma.presentation.media

import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MusicServiceConnection(context: Context, serviceComponent: ComponentName) {

    private val _ldIsConnected = MutableLiveData<Boolean>()
        .apply { postValue(false) }
    val ldIsConnected: LiveData<Boolean> = _ldIsConnected

    private val _ldNetworkFailure = MutableLiveData<Boolean>()
        .apply { postValue(false) }
    val ldNetworkFailure: LiveData<Boolean> = _ldNetworkFailure

    val ldIsPlaying = MutableLiveData<Boolean>()
        .apply { postValue(false) }

    val ldLoading = MutableLiveData<Boolean>()
        .apply { postValue(false) }

    private lateinit var mediaController: MediaControllerCompat

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback(context)
    private val mediaBrowser = MediaBrowserCompat(
        context,
        serviceComponent,
        mediaBrowserConnectionCallback,
        null
    ).apply { connect() }

    val transportControls: MediaControllerCompat.TransportControls
        get() = mediaController.transportControls

    private inner class MediaBrowserConnectionCallback(private val context: Context) :
        MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            mediaController = MediaControllerCompat(context, mediaBrowser.sessionToken).apply {
                registerCallback(MediaControllerCallback())
            }
            _ldIsConnected.postValue(true)
        }

        override fun onConnectionSuspended() {
            _ldIsConnected.postValue(false)
        }

        override fun onConnectionFailed() {
            _ldIsConnected.postValue(false)
        }
    }

    private inner class MediaControllerCallback : MediaControllerCompat.Callback() {

        override fun onPlaybackStateChanged(state: PlaybackStateCompat) {
            super.onPlaybackStateChanged(state)
            when (state.state) {
                PlaybackStateCompat.ACTION_STOP.toInt() -> ldIsPlaying.postValue(false)
                PlaybackStateCompat.ACTION_PLAY.toInt(),
                PlaybackStateCompat.STATE_PLAYING -> ldLoading.postValue(true)
            }
        }

        override fun onSessionEvent(event: String?, extras: Bundle?) {
            super.onSessionEvent(event, extras)
            when (event) {
                PLAYER_START -> ldIsPlaying.postValue(true)
                NETWORK_FAILURE -> _ldNetworkFailure.postValue(true)
            }
        }

        override fun onSessionDestroyed() {
            mediaBrowserConnectionCallback.onConnectionSuspended()
            mediaBrowser.disconnect()
        }
    }

}
