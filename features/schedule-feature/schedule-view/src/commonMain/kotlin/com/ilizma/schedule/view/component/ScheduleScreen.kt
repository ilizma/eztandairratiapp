package com.ilizma.schedule.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenIntent
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel,
    paddingValues: PaddingValues,
) {
    BackHandler { viewModel.onIntent(ScheduleScreenIntent.Back) }
    ScheduleScreenContent(
        viewModel = viewModel,
        paddingValues = paddingValues,
    )
}

@Composable
internal fun ScheduleScreenContent(
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


@Composable
internal fun Schedule(
    paddingValues: PaddingValues,
    list: List<Day>,
    onClick: (Day) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = paddingValues,
    ) {
        items(
            items = list,
            key = { it.id },
        ) { day ->
            DayRow(
                day = day,
                onClick = { onClick(day) },
            )
        }
    }
}

@Composable
private fun DayRow(
    day: Day,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            )
            .clickable { onClick() },
    ) {
        Text(
            text = day.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
        )
    }
}
