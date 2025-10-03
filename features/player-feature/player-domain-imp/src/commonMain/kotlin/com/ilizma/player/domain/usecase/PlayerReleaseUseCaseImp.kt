package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository

class PlayerReleaseUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerReleaseUseCase {

    override fun invoke() {
        repository.release()
    }

}