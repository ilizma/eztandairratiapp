package com.ilizma.player.framework

import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.helper.MusicHelper
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import platform.CoreMedia.CMTime

class PlayerFrameworkImp(
    private val musicHelper: MusicHelper,
    private val _playerState: MutableStateFlow<PlayerState>,
) : PlayerFramework {

    @OptIn(ExperimentalForeignApi::class)
    private val observer: (CValue<CMTime>) -> Unit =  {
        when {
            musicHelper.isPlaying() -> _playerState.value = PlayerState.Playing
            musicHelper.isLoading() -> _playerState.value = PlayerState.Loading
        }
    }

    override fun getState(
    ): Flow<PlayerState> = _playerState

    @OptIn(ExperimentalForeignApi::class)
    override fun play() {
        _playerState.value = PlayerState.Loading
        musicHelper.play(observer)
    }

    override fun stop() {
        musicHelper.stop()
        _playerState.value = PlayerState.Stopped
    }

    override fun cancel() {
        /* Do nothing */
    }

}