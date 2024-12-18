package com.ilizma.schedule.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel

@Composable
actual fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel,
    paddingValues: PaddingValues,
) {
    ScheduleScreenContent(
        viewModel = viewModel,
        paddingValues = paddingValues,
    )
}