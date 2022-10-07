package com.ilizma.player.data.mapper

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.data.model.PlayerState as DataPlayerState
import com.ilizma.player.framework.model.PlayerState as FrameworkPlayerState

class PlayerStateMapper {

    fun toDomain(
        state: DataPlayerState,
    ): PlayerState = when (state) {
        DataPlayerState.Playing -> PlayerState.Playing
        DataPlayerState.Loading -> PlayerState.Loading
        DataPlayerState.Stopped -> PlayerState.Stopped
        DataPlayerState.Error -> PlayerState.Error
    }

    fun toData(
        state: FrameworkPlayerState,
    ): DataPlayerState = when (state) {
        FrameworkPlayerState.Playing -> DataPlayerState.Playing
        FrameworkPlayerState.Loading -> DataPlayerState.Loading
        FrameworkPlayerState.Stopped -> DataPlayerState.Stopped
        FrameworkPlayerState.Error -> DataPlayerState.Error
    }

}