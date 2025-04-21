package com.ilizma.player.framework

import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.framework.helper.MusicHelper
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow

class PlayerFrameworkImp(
    private val musicHelper: MusicHelper,
) : PlayerFramework {

    override fun getState(
    ): Flow<PlayerState> = musicHelper.playerState

    @OptIn(ExperimentalForeignApi::class)
    override fun play() {
        musicHelper.play()
    }

    override fun stop() {
        musicHelper.stop()
    }

    override fun cancel() {
        /* Do nothing */
    }

}