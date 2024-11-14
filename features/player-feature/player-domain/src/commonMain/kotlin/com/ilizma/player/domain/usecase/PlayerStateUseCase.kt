package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import kotlinx.coroutines.flow.Flow

interface PlayerStateUseCase {

    operator fun invoke(): Flow<PlayerState>

}