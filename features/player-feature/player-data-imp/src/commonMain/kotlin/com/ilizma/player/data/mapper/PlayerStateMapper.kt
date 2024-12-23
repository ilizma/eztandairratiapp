package com.ilizma.player.data.mapper

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.data.model.PlayerState as DataPlayerState
import com.ilizma.player.framework.model.PlayerState as FrameworkPlayerState

class PlayerStateMapper {

    fun from(
        state: DataPlayerState,
    ): PlayerState = when (state) {
        DataPlayerState.Playing -> PlayerState.Playing
        DataPlayerState.Loading -> PlayerState.Loading
        DataPlayerState.Stopped -> PlayerState.Stopped
        DataPlayerState.Error.Malformed -> PlayerState.Error.Malformed
        DataPlayerState.Error.Unsupported -> PlayerState.Error.Unsupported
        DataPlayerState.Error.Timeout -> PlayerState.Error.Timeout
        DataPlayerState.Error.Network -> PlayerState.Error.Network
        DataPlayerState.Error.MediaDisconnected -> PlayerState.Error.MediaDisconnected
        DataPlayerState.Error.Unknown -> PlayerState.Error.Unknown
        DataPlayerState.Error.GenericError -> PlayerState.Error.GenericError
    }

    fun from(
        state: FrameworkPlayerState,
    ): DataPlayerState = when (state) {
        FrameworkPlayerState.Playing -> DataPlayerState.Playing
        FrameworkPlayerState.Loading -> DataPlayerState.Loading
        FrameworkPlayerState.Stopped -> DataPlayerState.Stopped
        FrameworkPlayerState.Error.Malformed -> DataPlayerState.Error.Malformed
        FrameworkPlayerState.Error.Unsupported -> DataPlayerState.Error.Unsupported
        FrameworkPlayerState.Error.Timeout -> DataPlayerState.Error.Timeout
        FrameworkPlayerState.Error.Network -> DataPlayerState.Error.Network
        FrameworkPlayerState.Error.MediaDisconnected -> DataPlayerState.Error.MediaDisconnected
        FrameworkPlayerState.Error.Unknown -> DataPlayerState.Error.Unknown
        FrameworkPlayerState.Error.GenericError -> DataPlayerState.Error.GenericError
    }

}