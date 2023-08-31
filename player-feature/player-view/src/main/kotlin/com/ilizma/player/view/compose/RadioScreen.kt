package com.ilizma.player.view.compose

import android.view.Menu
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.resources.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Preview
@Composable
fun RadioScreen(
    @PreviewParameter(RadioScreenPreviewProvider::class) viewModel: RadioScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(),
    snackbarHostState: SnackbarHostState = SnackbarHostState(),
) {
    viewModel.playerState
        .collectAsState(PlayerState.Stopped)
        .value
        .let {
            ScreenState(
                state = it,
                snackbarHostState = snackbarHostState,
                viewModel = viewModel,
                paddingValues = paddingValues
            )
        }
}

@Composable
private fun ScreenState(
    state: PlayerState,
    snackbarHostState: SnackbarHostState,
    viewModel: RadioScreenViewModel,
    paddingValues: PaddingValues
) {
    ErrorSnackbar(
        state = state,
        snackbarHostState = snackbarHostState,
        viewModel = viewModel,
    )
    ScreenBox(paddingValues, state, viewModel)
    // TODO: 2023/08/30 Cast MiniController
}

@Composable
private fun ErrorSnackbar(
    state: PlayerState,
    snackbarHostState: SnackbarHostState,
    viewModel: RadioScreenViewModel
) {
    if (state is PlayerState.Error) {
        val message = stringResource(errorRes(state))
        val actionLabel = stringResource(R.string.retry)
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
            ).let { snackbarResult ->
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    viewModel.onPlay()
                }
            }
        }
    }
}

@Composable
private fun ScreenBox(
    paddingValues: PaddingValues,
    state: PlayerState,
    viewModel: RadioScreenViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(id = R.drawable.img_eztanda),
            contentDescription = "Eztanda image",
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                when (state) {
                    PlayerState.Stopped -> viewModel.onPlay()
                    PlayerState.Playing -> viewModel.onStop()
                    else -> { /* no-op */ }
                }
            },
        ) {
            when (state) {
                PlayerState.Loading -> CircularProgressIndicator()
                else -> Icon(
                    painter = painterResource(
                        id = when (state) {
                            PlayerState.Loading,
                            is PlayerState.Error,
                            PlayerState.Stopped -> R.drawable.ic_play

                            PlayerState.Playing -> R.drawable.ic_stop
                        }
                    ),
                    contentDescription = "Play",
                )
            }

        }
    }
}

private fun errorRes(
    errorState: PlayerState.Error
): Int = when (errorState) {
    PlayerState.Error.Malformed -> R.string.malformed_media
    PlayerState.Error.Unsupported -> R.string.unsupported_media
    PlayerState.Error.Timeout -> R.string.timeout_message
    PlayerState.Error.Network -> R.string.no_internet
    PlayerState.Error.MediaDisconnected -> R.string.media_disconnected
    PlayerState.Error.Unknown -> R.string.unknown_error
    PlayerState.Error.GenericError -> R.string.generic_error
}

private class RadioScreenPreviewProvider : PreviewParameterProvider<RadioScreenViewModel> {
    override val values: Sequence<RadioScreenViewModel> = sequenceOf(
        FakeViewModel(PlayerState.Stopped),
        FakeViewModel(PlayerState.Loading),
        FakeViewModel(PlayerState.Playing),
        FakeViewModel(PlayerState.Error.GenericError),
    )

    class FakeViewModel(
        state: PlayerState,
    ) : RadioScreenViewModel() {
        override val navigationAction: LiveData<RadioScreenNavigationAction>
            get() = TODO("Not yet implemented")

        override val playerState: Flow<PlayerState> = flowOf(state)

        override fun onPlay() {
        }

        override fun onStop() {
        }

        override fun onBack() {
        }

        override fun setUpMediaRouteButton(menu: Menu, menuResourceId: Int) {
        }

    }
}
