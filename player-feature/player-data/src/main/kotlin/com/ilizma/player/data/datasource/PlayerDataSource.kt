package com.ilizma.player.data.datasource

import com.ilizma.player.data.model.PlayerState
import io.reactivex.rxjava3.core.Observable

interface PlayerDataSource {

    fun getState(): Observable<PlayerState>

    fun play()

    fun stop()

}