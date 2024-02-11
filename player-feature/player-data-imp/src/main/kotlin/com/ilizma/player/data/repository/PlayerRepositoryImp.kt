package com.ilizma.player.data.repository

import com.ilizma.player.data.datasource.PlayerDataSource
import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlayerRepositoryImp(
    private val dataSource: PlayerDataSource,
    private val mapper: PlayerStateMapper,
) : PlayerRepository {

    override fun getState(
    ): Flow<PlayerState> = dataSource.getState()
        .map { mapper.toDomain(it) }

    override fun play() {
        dataSource.play()
    }

    override fun stop() {
        dataSource.stop()
    }

}