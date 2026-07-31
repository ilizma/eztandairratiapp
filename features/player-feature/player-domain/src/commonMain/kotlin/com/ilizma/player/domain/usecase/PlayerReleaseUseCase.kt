package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository

class PlayerReleaseUseCase(
    private val repository: PlayerRepository,
) {

    operator fun invoke() {
        repository.release()
    }

}