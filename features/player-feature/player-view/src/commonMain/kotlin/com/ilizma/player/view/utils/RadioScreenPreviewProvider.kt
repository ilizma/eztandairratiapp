package com.ilizma.player.view.utils

import com.ilizma.player.presentation.model.PlayerState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class RadioScreenPreviewProvider : PreviewParameterProvider<PlayerState> {
    override val values: Sequence<PlayerState> = sequenceOf(
        PlayerState.Stopped,
        PlayerState.Loading,
        PlayerState.Playing,
        PlayerState.Error.GenericError,
    )
}