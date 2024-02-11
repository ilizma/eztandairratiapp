package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import kotlinx.coroutines.flow.Flow

class PlayerStateUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerStateUseCase {

    override fun invoke(
    ): Flow<PlayerState> = repository.getState()

}