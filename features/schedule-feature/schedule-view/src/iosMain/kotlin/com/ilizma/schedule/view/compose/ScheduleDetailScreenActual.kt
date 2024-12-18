package com.ilizma.schedule.view.compose

import androidx.compose.runtime.Composable
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel

@Composable
actual fun ScheduleDetailScreen(
    viewModel: ScheduleDetailScreenViewModel,
) {
    ScheduleDetailScreenContent(
        viewModel = viewModel,
    )
}