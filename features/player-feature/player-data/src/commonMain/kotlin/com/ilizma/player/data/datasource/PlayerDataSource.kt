package com.ilizma.player.data.datasource

import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.data.model.PlayerState
import com.ilizma.player.framework.PlayerFramework
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerDataSource(
    private val framework: PlayerFramework,
    private val mapper: PlayerStateMapper,
) {

    fun getState(
    ): Flow<PlayerState> = framework.getState()
        .map { mapper.from(it) }

    fun play() {
        framework.play()
    }

    fun stop() {
        framework.stop()
    }

    fun release() {
        framework.release()
    }

}