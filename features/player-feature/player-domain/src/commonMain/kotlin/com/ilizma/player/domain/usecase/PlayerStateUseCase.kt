package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow

class PlayerStateUseCase(
    private val repository: PlayerRepository,
) {

    operator fun invoke(
    ): Flow<PlayerState> = repository.getState()

}