package com.ilizma.schedule.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenIntent
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel,
    paddingValues: PaddingValues,
) {
    viewModel.days
        .collectAsStateMultiplatform(
            initialValue = Days(listOf()),
        ).value
        .let { days ->
            Schedule(
                paddingValues = paddingValues,
                list = days.dayList,
                onClick = { day ->
                    ScheduleScreenIntent.Click(
                        day = day,
                    ).let { viewModel.onIntent(it) }
                }
            )
        }
}