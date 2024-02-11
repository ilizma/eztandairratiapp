package com.ilizma.player.data.datasource

import com.ilizma.player.data.model.PlayerState
import kotlinx.coroutines.flow.Flow

interface PlayerDataSource {

    fun getState(): Flow<PlayerState>

    fun play()

    fun stop()

}