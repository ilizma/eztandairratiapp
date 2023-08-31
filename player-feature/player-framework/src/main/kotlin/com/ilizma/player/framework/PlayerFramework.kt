package com.ilizma.player.framework

import com.ilizma.player.framework.model.PlayerState
import io.reactivex.rxjava3.core.Observable

interface PlayerFramework {

    fun getState(): Observable<PlayerState>

    fun play()

    fun stop()

    fun cancel()

}