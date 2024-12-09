package com.ilizma.schedule.view.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenIntent
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel,
    paddingValues: PaddingValues,
) {

    BackHandler { viewModel.onIntent(ScheduleScreenIntent.Back) }

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