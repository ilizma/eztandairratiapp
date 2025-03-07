package com.ilizma.player.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.ilizma.player.presentation.model.PlayerState
import com.ilizma.player.presentation.model.RadioScreenIntent
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.resources.Res
import com.ilizma.resources.generic_error
import com.ilizma.resources.img_eztanda
import com.ilizma.resources.malformed_media
import com.ilizma.resources.media_disconnected
import com.ilizma.resources.no_internet
import com.ilizma.resources.retry
import com.ilizma.resources.timeout_message
import com.ilizma.resources.unknown_error
import com.ilizma.resources.unsupported_media
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RadioScreen(
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

@Composable
internal fun RadioScreenContent(
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

@Composable
internal fun ScreenState(
    state: PlayerState,
    snackbarHostState: SnackbarHostState,
    onIntent: (RadioScreenIntent) -> Unit,
    paddingValues: PaddingValues,
) {
    ErrorSnackbar(
        state = state,
        snackbarHostState = snackbarHostState,
        onRetry = { onIntent(RadioScreenIntent.Play) },
    )
    ScreenBox(
        paddingValues = paddingValues,
        state = state,
        onIntent = onIntent
    )
    // TODO: 2023/08/30 Cast MiniController
}

@Composable
private fun ErrorSnackbar(
    state: PlayerState,
    snackbarHostState: SnackbarHostState,
    onRetry: () -> Unit,
) {
    if (state is PlayerState.Error) {
        val message = stringResource(errorRes(state))
        val actionLabel = stringResource(Res.string.retry)
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = actionLabel,
            ).let { snackbarResult ->
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    onRetry()
                }
            }
        }
    }
}

@Composable
private fun ScreenBox(
    paddingValues: PaddingValues,
    state: PlayerState,
    onIntent: (RadioScreenIntent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(Res.drawable.img_eztanda),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiary),
            contentDescription = "Eztanda image",
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                when (state) {
                    PlayerState.Stopped -> onIntent(RadioScreenIntent.Play)
                    PlayerState.Playing -> onIntent(RadioScreenIntent.Stop)
                    else -> { /* no-op */
                    }
                }
            },
        ) {
            when (state) {
                PlayerState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = LocalContentColor.current,
                )

                else -> Icon(
                    imageVector = when (state) {
                        PlayerState.Loading,
                        is PlayerState.Error,
                        PlayerState.Stopped,
                            -> Icons.Default.PlayArrow

                        PlayerState.Playing -> Icons.Default.Stop
                    },
                    contentDescription = "Play & Stop",
                )
            }

        }
    }
}

private fun errorRes(
    errorState: PlayerState.Error,
): StringResource = when (errorState) {
    PlayerState.Error.Malformed -> Res.string.malformed_media
    PlayerState.Error.Unsupported -> Res.string.unsupported_media
    PlayerState.Error.Timeout -> Res.string.timeout_message
    PlayerState.Error.Network -> Res.string.no_internet
    PlayerState.Error.MediaDisconnected -> Res.string.media_disconnected
    PlayerState.Error.Unknown -> Res.string.unknown_error
    PlayerState.Error.GenericError -> Res.string.generic_error
}
