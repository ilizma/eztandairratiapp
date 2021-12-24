package com.ilizma.player.domain.repository

import com.ilizma.player.domain.model.PlayerState
import io.reactivex.rxjava3.core.Observable

interface PlayerRepository {

    fun getState(): Observable<PlayerState>

    fun play()

    fun stop()

}