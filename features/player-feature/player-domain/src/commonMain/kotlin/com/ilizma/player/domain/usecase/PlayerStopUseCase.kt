package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository

class PlayerStopUseCase(
    private val repository: PlayerRepository,
) {

    operator fun invoke() {
        repository.stop()
    }

}