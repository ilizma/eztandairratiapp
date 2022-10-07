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
        PlayerState.Error -> PresentationPlayerState.Error
    }

}