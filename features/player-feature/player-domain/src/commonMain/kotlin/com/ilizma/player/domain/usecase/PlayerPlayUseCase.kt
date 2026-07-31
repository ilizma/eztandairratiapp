package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository

class PlayerPlayUseCase(
    private val repository: PlayerRepository,
) {

    operator fun invoke() {
        repository.play()
    }

}