package com.ilizma.main.view.widget.mapper

import com.ilizma.player.framework.model.PlayerState as FrameworkPlayerState
import com.ilizma.main.view.widget.model.PlayerState as WidgetPlayerState

class PlayerStateMapper {

    fun from(
        viewModelState: FrameworkPlayerState,
    ): WidgetPlayerState = when (viewModelState) {
        FrameworkPlayerState.Playing -> WidgetPlayerState.Playing
        FrameworkPlayerState.Loading -> WidgetPlayerState.Loading
        FrameworkPlayerState.Stopped -> WidgetPlayerState.Stopped
        FrameworkPlayerState.Error.Malformed -> WidgetPlayerState.Error.Malformed
        FrameworkPlayerState.Error.Unsupported -> WidgetPlayerState.Error.Unsupported
        FrameworkPlayerState.Error.Timeout -> WidgetPlayerState.Error.Timeout
        FrameworkPlayerState.Error.Network -> WidgetPlayerState.Error.Network
        FrameworkPlayerState.Error.MediaDisconnected -> WidgetPlayerState.Error.MediaDisconnected
        FrameworkPlayerState.Error.Unknown -> WidgetPlayerState.Error.Unknown
        FrameworkPlayerState.Error.GenericError -> WidgetPlayerState.Error.GenericError
    }
}