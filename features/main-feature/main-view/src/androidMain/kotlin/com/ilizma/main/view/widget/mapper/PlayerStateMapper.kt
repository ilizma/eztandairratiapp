package com.ilizma.main.view.widget.mapper

import com.ilizma.player.framework.model.PlayerState
import com.ilizma.player.domain.model.PlayerState as DomainPlayerState
import com.ilizma.main.view.widget.model.PlayerState as WidgetPlayerState

class PlayerStateMapper {

    fun from(
        domainState: DomainPlayerState,
    ): WidgetPlayerState = when (domainState) {
        DomainPlayerState.Playing -> WidgetPlayerState.Playing
        DomainPlayerState.Loading -> WidgetPlayerState.Loading
        DomainPlayerState.Stopped -> WidgetPlayerState.Stopped
        is DomainPlayerState.Error -> WidgetPlayerState.Error.GenericError
    }

    fun from(
        frameworkState: PlayerState,
    ): WidgetPlayerState = when (frameworkState) {
        PlayerState.Playing -> WidgetPlayerState.Playing
        PlayerState.Loading -> WidgetPlayerState.Loading
        PlayerState.Stopped -> WidgetPlayerState.Stopped
        PlayerState.Error.Malformed -> WidgetPlayerState.Error.Malformed
        PlayerState.Error.Unsupported -> WidgetPlayerState.Error.Unsupported
        PlayerState.Error.Timeout -> WidgetPlayerState.Error.Timeout
        PlayerState.Error.Network -> WidgetPlayerState.Error.Network
        PlayerState.Error.MediaDisconnected -> WidgetPlayerState.Error.MediaDisconnected
        PlayerState.Error.Unknown -> WidgetPlayerState.Error.Unknown
        PlayerState.Error.GenericError -> WidgetPlayerState.Error.GenericError
    }
}
