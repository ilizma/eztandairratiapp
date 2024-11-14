package com.ilizma.player.framework

import com.ilizma.player.framework.model.PlayerState
import kotlinx.coroutines.flow.Flow

interface PlayerFramework {

    fun getState(): Flow<PlayerState>

    fun play()

    fun stop()

    fun cancel()

}