package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import io.reactivex.rxjava3.core.Observable

interface PlayerUseCase {

    fun getState(): Observable<PlayerState>

    fun play()

    fun stop()

}