package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository

class PlayerPlayUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerPlayUseCase {

    override fun invoke() {
        repository.play()
    }

}