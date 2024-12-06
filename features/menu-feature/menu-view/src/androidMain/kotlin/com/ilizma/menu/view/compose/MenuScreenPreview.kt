package com.ilizma.menu.view.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme

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