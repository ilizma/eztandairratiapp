package com.ilizma.main.view.widget.utils

import com.ilizma.main.view.widget.model.PlayerState
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class PlayerWidgetPreviewProvider : PreviewParameterProvider<PlayerState> {
    override val values: Sequence<PlayerState> = sequenceOf(
        PlayerState.Stopped,
        PlayerState.Loading,
        PlayerState.Playing,
        PlayerState.Error.GenericError,
    )
}