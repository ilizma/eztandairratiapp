package com.ilizma.player.presentation.mapper

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.presentation.model.PlayerState as PresentationPlayerState

class PlayerStateMapper {

    fun toPresentation(
        state: PlayerState,
    ): PresentationPlayerState = when (state) {
        PlayerState.Playing -> PresentationPlayerState.Playing
        PlayerState.Loading -> PresentationPlayerState.Loading
        PlayerState.Stopped -> PresentationPlayerState.Stopped
        PlayerState.Error.Malformed -> PresentationPlayerState.Error.Malformed
        PlayerState.Error.Unsupported -> PresentationPlayerState.Error.Unsupported
        PlayerState.Error.Timeout -> PresentationPlayerState.Error.Timeout
        PlayerState.Error.Network -> PresentationPlayerState.Error.Network
        PlayerState.Error.MediaDisconnected -> PresentationPlayerState.Error.MediaDisconnected
        PlayerState.Error.Unknown -> PresentationPlayerState.Error.Unknown
        PlayerState.Error.GenericError -> PresentationPlayerState.Error.GenericError
    }

}