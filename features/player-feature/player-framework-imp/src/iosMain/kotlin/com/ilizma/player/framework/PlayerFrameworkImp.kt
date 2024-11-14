package com.ilizma.player.framework

import com.ilizma.player.framework.model.PlayerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class PlayerFrameworkImp(
    private val _playerState: MutableStateFlow<PlayerState>,
) : PlayerFramework {

    override fun getState(
    ): Flow<PlayerState> = _playerState

    override fun play() {
        //TODO("Not yet implemented")
    }

    override fun stop() {
        //TODO("Not yet implemented")
    }

    override fun cancel() {
        //TODO("Not yet implemented")
    }

}