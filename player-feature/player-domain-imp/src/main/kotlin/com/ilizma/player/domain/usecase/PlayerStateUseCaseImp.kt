package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import io.reactivex.rxjava3.core.Observable

class PlayerStateUseCaseImp(
    private val repository: PlayerRepository,
) : PlayerStateUseCase {

    override fun invoke(
    ): Observable<PlayerState> = repository.getState()

}