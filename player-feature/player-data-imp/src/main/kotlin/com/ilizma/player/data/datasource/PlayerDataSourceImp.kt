package com.ilizma.player.data.datasource

import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.data.model.PlayerState
import com.ilizma.player.framework.PlayerFramework
import io.reactivex.rxjava3.core.Observable

class PlayerDataSourceImp(
    private val framework: PlayerFramework,
    private val mapper: PlayerStateMapper,
) : PlayerDataSource {

    override fun getState(
    ): Observable<PlayerState> = framework.getState()
        .map { mapper.toData(it) }

    override fun play() {
        framework.play()
    }

    override fun stop() {
        framework.stop()
    }

}