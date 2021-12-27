package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import io.reactivex.rxjava3.core.Observable

class PlayerPlayUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerPlayUseCase {

    override fun invoke() {
        repository.play()
    }

}