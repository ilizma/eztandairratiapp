package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import io.reactivex.rxjava3.core.Observable

class PlayerUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerUseCase {

    override fun getState(
    ): Observable<PlayerState> = repository.getState()

    override fun play() {
        repository.play()
    }

    override fun stop() {
        repository.stop()
    }

}