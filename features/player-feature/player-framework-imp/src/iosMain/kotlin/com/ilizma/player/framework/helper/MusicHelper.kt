package com.ilizma.player.framework.helper

import com.ilizma.player.framework.imp.BuildKonfig
import com.ilizma.player.framework.model.PlayerState
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.AVAudioSessionPortOverrideSpeaker
import platform.AVFAudio.setActive
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.Foundation.NSURL
import platform.MediaPlayer.MPMediaItemArtwork
import platform.MediaPlayer.MPMediaItemPropertyArtist
import platform.MediaPlayer.MPMediaItemPropertyArtwork
import platform.MediaPlayer.MPMediaItemPropertyTitle
import platform.MediaPlayer.MPNowPlayingInfoCenter
import platform.MediaPlayer.MPNowPlayingInfoPropertyPlaybackRate
import platform.MediaPlayer.MPRemoteCommandCenter
import platform.MediaPlayer.MPRemoteCommandHandlerStatusSuccess
import platform.UIKit.UIImage

class MusicHelper {

    private val player: AVPlayer = BuildKonfig.AUDIO_URL
        .let { NSURL.URLWithString(URLString = it)!! }
        .let { AVPlayer(it) }

    private var session: AVAudioSession? = null

    private val _playerState: MutableStateFlow<PlayerState> = MutableStateFlow(PlayerState.Stopped)
    val playerState: StateFlow<PlayerState> = _playerState

    init {
        setUpAudioSession()
    }

    fun play() {
        stop()
        _playerState.tryEmit(PlayerState.Loading)
        setupRemoteCommandCenter()
        updateNowPlayingInfo(
            title = "Eztanda Irratia",
            artist = "107.6 FM",
            iconName = "RadioIcon",
        )
        player.play()
        _playerState.tryEmit(PlayerState.Playing)
    }

    fun stop() {
        player.pause()
        _playerState.tryEmit(PlayerState.Stopped)
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setUpAudioSession() {
        try {
            session = AVAudioSession.sharedInstance()
                .apply {
                    setCategory(
                        category = AVAudioSessionCategoryPlayback,
                        error = null,
                    )
                    setActive(
                        active = true,
                        error = null,
                    )
                    overrideOutputAudioPort(
                        portOverride = AVAudioSessionPortOverrideSpeaker,
                        error = null,
                    )
                }
        } catch (e: Exception) {
            println("Error setting up audio session: ${e.message}")
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun updateNowPlayingInfo(
        title: String,
        artist: String,
        iconName: String,
    ) {
        val nowPlayingInfo = mutableMapOf<Any?, Any?>(
            MPMediaItemPropertyTitle to title,
            MPMediaItemPropertyArtist to artist,
            MPNowPlayingInfoPropertyPlaybackRate to 1.0,
            MPMediaItemPropertyArtwork to UIImage.imageNamed(name = iconName)
                ?.let { image ->
                    MPMediaItemArtwork(
                        boundsSize = image.size,
                        requestHandler = { image },
                    )
                },
        )

        MPNowPlayingInfoCenter.defaultCenter().nowPlayingInfo = nowPlayingInfo
    }

    private fun setupRemoteCommandCenter() {
        MPRemoteCommandCenter.sharedCommandCenter().apply {
            playCommand.enabled = true
            playCommand.addTargetWithHandler(
                handler = {
                    play()
                    MPRemoteCommandHandlerStatusSuccess
                },
            )

            pauseCommand.enabled = true
            pauseCommand.addTargetWithHandler(
                handler = {
                    stop()
                    MPRemoteCommandHandlerStatusSuccess
                },
            )
        }
    }
}

