package com.ilizma.player.domain.repository

import com.ilizma.player.domain.model.PlayerState
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    fun getState(): Flow<PlayerState>

    fun play()

    fun stop()

}