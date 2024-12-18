package com.ilizma.player.view.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenIntent
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme

@Composable
actual fun RadioScreen(
    viewModel: RadioScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    BackHandler { viewModel.onIntent(RadioScreenIntent.Back) }
    RadioScreenContent(
        viewModel = viewModel,
        paddingValues = paddingValues,
        snackbarHostState = snackbarHostState,
    )
}

@PreviewLightDark
@Composable
private fun RadioScreenPreview(
    @PreviewParameter(RadioScreenPreviewProvider::class) state: PlayerState,
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    EztandaIrratiappTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
        ) { paddingValues ->
            ScreenState(
                state = state,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
                onIntent = {},
            )
        }
    }
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<PlayerState> {
    override val values: Sequence<PlayerState> = sequenceOf(
        PlayerState.Stopped,
        PlayerState.Loading,
        PlayerState.Playing,
        PlayerState.Error.GenericError,
    )
}