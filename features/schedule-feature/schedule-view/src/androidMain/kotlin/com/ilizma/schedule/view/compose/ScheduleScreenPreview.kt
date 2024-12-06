package com.ilizma.schedule.view.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.schedule.presentation.model.Day

@PreviewLightDark
@Composable
private fun ScheduleScreenPreview() {
    EztandaIrratiappTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            Schedule(
                paddingValues = paddingValues,
                list = listOf(
                    Day(id = 1, name = "Monday"),
                    Day(id = 2, name = "Tuesday"),
                ),
                onClick = {},
            )
        }
    }
}