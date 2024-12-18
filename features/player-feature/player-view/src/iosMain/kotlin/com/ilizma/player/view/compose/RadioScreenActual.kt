package com.ilizma.player.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel

@Composable
actual fun RadioScreen(
    viewModel: RadioScreenViewModel,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
) {
    RadioScreenContent(
        viewModel = viewModel,
        paddingValues = paddingValues,
        snackbarHostState = snackbarHostState,
    )
}