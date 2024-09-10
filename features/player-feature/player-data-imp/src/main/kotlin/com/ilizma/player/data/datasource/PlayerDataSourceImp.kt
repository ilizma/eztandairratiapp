package com.ilizma.player.data.datasource

import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.data.model.PlayerState
import com.ilizma.player.framework.PlayerFramework
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerDataSourceImp(
    private val framework: PlayerFramework,
    private val mapper: PlayerStateMapper,
) : PlayerDataSource {

    override fun getState(
    ): Flow<PlayerState> = framework.getState()
        .map { mapper.toData(it) }

    override fun play() {
        framework.play()
    }

    override fun stop() {
        framework.stop()
    }

}