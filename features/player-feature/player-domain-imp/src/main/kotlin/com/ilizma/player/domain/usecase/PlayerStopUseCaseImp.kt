package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository

class PlayerStopUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerStopUseCase {

    override fun invoke() {
        repository.stop()
    }

}