package com.ilizma.player.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun RadioScreen(
    viewModel: RadioScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {

    viewModel.playerState
        .collectAsStateMultiplatform(
            initialValue = PlayerState.Stopped,
        ).value
        .let {
            ScreenState(
                state = it,
                snackbarHostState = snackbarHostState,
                onIntent = { viewModel.onIntent(it) },
                paddingValues = paddingValues
            )
        }
}