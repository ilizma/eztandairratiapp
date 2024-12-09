package com.ilizma.menu.view.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.ilizma.menu.presentation.model.MenuScreenIntent
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme

@Composable
actual fun MenuScreen(
    viewModel: MenuScreenViewModel,
    paddingValues: PaddingValues,
) {

    BackHandler { viewModel.onIntent(MenuScreenIntent.Back) }
    Content(
        paddingValues = paddingValues,
        onIntent = { viewModel.onIntent(it) },
    )
}

@PreviewLightDark
@Composable
private fun MenuScreenPreview() {
    EztandaIrratiappTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            Content(
                paddingValues = paddingValues,
                onIntent = {},
            )
        }
    }
}