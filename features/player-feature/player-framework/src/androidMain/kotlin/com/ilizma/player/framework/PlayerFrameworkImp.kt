package com.ilizma.player.framework

import androidx.core.os.bundleOf
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionCommand
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import com.ilizma.player.framework.imp.BuildKonfig
import com.ilizma.player.framework.model.PlayerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

private const val CANCEL_NOTIFICATION = "CANCEL_NOTIFICATION"

class PlayerFrameworkImp(
    mediaControllerBuilder: MediaController.Builder,
    private val _playerState: MutableStateFlow<PlayerState>,
) : PlayerFramework {

    private var mediaController: MediaController? = null
    private var controllerFuture: ListenableFuture<MediaController>? = null

    private val playerListener = object : Player.Listener {
        override fun onIsLoadingChanged(isLoading: Boolean) {
            super.onIsLoadingChanged(isLoading)
            if (isLoading) {
                PlayerState.Loading
                    .let { _playerState.value = it }
            }
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            when {
                isPlaying -> PlayerState.Playing
                    .let { _playerState.value = it }

                else -> PlayerState.Stopped
                    .let { _playerState.value = it }

                /** Playback is paused, ended, suppressed, or
                Player is buffering, stopped or failed.
                Check player.playWhenReady, player.playbackState,
                player.playbackSuppressionReason and player.playerError for details. */
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            super.onPlayerError(error)
            when (error.errorCode) {
                PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
                PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED,
                PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND,
                PlaybackException.ERROR_CODE_IO_NO_PERMISSION,
                PlaybackException.ERROR_CODE_IO_UNSPECIFIED,
                PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE,
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT,
                PlaybackException.ERROR_CODE_IO_READ_POSITION_OUT_OF_RANGE,

                PlaybackException.ERROR_CODE_FAILED_RUNTIME_CHECK,
                PlaybackException.ERROR_CODE_DECODER_INIT_FAILED,
                PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED,
                PlaybackException.ERROR_CODE_DECODING_FAILED,
                PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES,
                    -> PlayerState.Error.GenericError

                PlaybackException.ERROR_CODE_PARSING_MANIFEST_MALFORMED,
                PlaybackException.ERROR_CODE_PARSING_CONTAINER_MALFORMED,
                    -> PlayerState.Error.Malformed

                PlaybackException.ERROR_CODE_PARSING_CONTAINER_UNSUPPORTED,
                PlaybackException.ERROR_CODE_PARSING_MANIFEST_UNSUPPORTED,
                PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED,
                    -> PlayerState.Error.Unsupported

                PlaybackException.ERROR_CODE_TIMEOUT -> PlayerState.Error.Timeout
                PlaybackException.ERROR_CODE_REMOTE_ERROR,
                PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED,
                PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED,
                    -> PlayerState.Error.Network

                PlaybackException.ERROR_CODE_UNSPECIFIED -> PlayerState.Error.Unknown
                else -> PlayerState.Error.Unknown
            }.let { _playerState.value = it }
        }
    }

    init {
        mediaControllerBuilder.buildAsync()
            .also { controllerFuture = it }
            .let {
                it.addListener(
                    {
                        try {
                            mediaController = it.get()
                                .also { it.addListener(playerListener) }
                        } catch (e: Exception) {
                            _playerState.value = PlayerState.Error.Unknown
                        }
                    },
                    MoreExecutors.directExecutor(),
                )
            }
    }

    override fun getState(
    ): Flow<PlayerState> = _playerState

    override fun play() {
        initMediaPlayer()
        mediaController?.prepare()
        mediaController?.play()
    }

    override fun stop() {
        mediaController?.stop()
        PlayerState.Stopped
            .let { _playerState.value = it }
        //MediaController.releaseFuture(controllerFuture)
    }

    override fun cancel() {
        SessionCommand(CANCEL_NOTIFICATION, bundleOf())
            .let { mediaController?.sendCustomCommand(it, bundleOf()) }
    }

    override fun release() {
        controllerFuture?.let { future ->
            mediaController?.removeListener(playerListener)
            MediaController.releaseFuture(future)
        }
        mediaController = null
        controllerFuture = null
        _playerState.value = PlayerState.Stopped
    }

    private fun initMediaPlayer() {
        mediaController
            ?.apply {
                MediaItem.fromUri(BuildKonfig.AUDIO_URL)
                    .let { setMediaItem(it) }
            }
    }
}