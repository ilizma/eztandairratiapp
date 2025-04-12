package com.ilizma.player.framework.helper

import com.ilizma.player.framework.imp.BuildKonfig
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.AVAudioSessionPortOverrideSpeaker
import platform.AVFAudio.setActive
import platform.AVFoundation.addPeriodicTimeObserverForInterval
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerTimeControlStatusPlaying
import platform.AVFoundation.AVPlayerItemPlaybackStalledNotification
import platform.AVFoundation.currentItem
import platform.AVFoundation.isPlaybackLikelyToKeepUp
import platform.AVFoundation.removeTimeObserver
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.timeControlStatus
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURL
import platform.darwin.NSEC_PER_SEC
import platform.MediaPlayer.MPMediaItemPropertyArtist
import platform.MediaPlayer.MPMediaItemPropertyTitle
import platform.MediaPlayer.MPNowPlayingInfoCenter
import platform.MediaPlayer.MPNowPlayingInfoPropertyPlaybackRate

class MusicHelper {

    private val player: AVPlayer = BuildKonfig.AUDIO_URL
        .let { NSURL.URLWithString(URLString = it)!! }
        .let { AVPlayer(it) }

    private var session: AVAudioSession? = null

    private var timeObserver: Any? = null

    init {
        setUpAudioSession()
    }

    fun isLoading(): Boolean = player.currentItem?.isPlaybackLikelyToKeepUp() != true

    fun isPlaying(): Boolean = player.timeControlStatus == AVPlayerTimeControlStatusPlaying

    @OptIn(ExperimentalForeignApi::class)
    fun play(
        observer: (CValue<CMTime>) -> Unit,
    ) {
        stop()
        startTimeObserver(observer = observer)
        showNotification()
        updateNowPlayingInfo(
            title = "Eztanda Irratia",
            artist = "107.6 FM",
        )
        player.play()
    }

    fun stop() {
        timeObserver
            ?.let { player.removeTimeObserver(observer = it) }
        timeObserver = null
        player.pause()
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
    private fun startTimeObserver(
        observer: (CValue<CMTime>) -> Unit,
    ) {
        timeObserver = CMTimeMakeWithSeconds(
            seconds = 1.0,
            preferredTimescale = NSEC_PER_SEC.toInt(),
        ).let {
            player.addPeriodicTimeObserverForInterval(
                interval = it,
                queue = null,
                usingBlock = observer,
            )
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun showNotification() {
        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemPlaybackStalledNotification,
            `object` = player.currentItem,
            queue = NSOperationQueue.mainQueue,
            usingBlock = {
                stop()
                session?.setActive(
                    active = false,
                    error = null,
                )
            },
        )
    }

    private fun updateNowPlayingInfo(
        title: String,
        artist: String,
    ) {
        val nowPlayingInfo = mutableMapOf<Any?, Any?>(
            MPMediaItemPropertyTitle to title,
            MPMediaItemPropertyArtist to artist,
            MPNowPlayingInfoPropertyPlaybackRate to 1.0
        )

        MPNowPlayingInfoCenter.defaultCenter().nowPlayingInfo = nowPlayingInfo
    }
}

