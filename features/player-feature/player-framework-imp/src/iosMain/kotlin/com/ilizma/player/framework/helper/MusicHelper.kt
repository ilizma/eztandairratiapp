package com.ilizma.player.framework.helper

import com.ilizma.player.framework.imp.BuildKonfig
import kotlinx.cinterop.*
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.*
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.*
import platform.darwin.NSEC_PER_SEC

class MusicHelper {

    private val player: AVPlayer = BuildKonfig.AUDIO_URL
        .let { NSURL.URLWithString(URLString = it)!! }
        .let { AVPlayer(it) }

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
        BuildKonfig.AUDIO_URL
            .let { NSURL.URLWithString(URLString = it) }
            ?.let { AVPlayerItem(uRL = it) }
            ?.let { player.replaceCurrentItemWithPlayerItem(item = it) }
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        timeObserver
            ?.let { player.removeTimeObserver(observer = it) }
        player.pause()
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setUpAudioSession() {
        try {
            AVAudioSession.sharedInstance()
                .apply {
                    setCategory(
                        category = AVAudioSessionCategoryPlayback,
                        error = null,
                    )
                    setActive(
                        active = true,
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

    private fun showNotification() {
        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemPlaybackStalledNotification,
            `object` = player.currentItem,
            queue = NSOperationQueue.mainQueue,
            usingBlock = { stop() },
        )
    }
}

