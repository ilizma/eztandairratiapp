package com.ilizma.menu.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel

@Composable
actual fun MenuScreen(
    viewModel: MenuScreenViewModel,
    paddingValues: PaddingValues,
) {
    Content(
        paddingValues = paddingValues,
        onIntent = { viewModel.onIntent(it) },
    )
}