package com.ilizma.schedule.view.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ilizma.schedule.presentation.model.Day
import com.ilizma.schedule.presentation.model.Days
import com.ilizma.schedule.presentation.model.ScheduleScreenNavigationAction
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun ScheduleScreen(
    viewModel: ScheduleScreenViewModel,
    paddingValues: PaddingValues,
) {
    viewModel.days
        .collectAsState(Days(listOf()))
        .value
        .let { days ->
            Schedule(
                paddingValues = paddingValues,
                list = days.dayList,
                onClick = { viewModel.onClick(it) }
            )
        }
}

@Composable
private fun Schedule(
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

@Preview(showBackground = true)
@Composable
private fun ScheduleScreenPreview(
    @PreviewParameter(ScheduleScreenPreviewProvider::class) viewModel: ScheduleScreenViewModel,
    paddingValues: PaddingValues = PaddingValues(),
) {
    ScheduleScreen(
        viewModel = viewModel,
        paddingValues = paddingValues,
    )
}

private class ScheduleScreenPreviewProvider : PreviewParameterProvider<ScheduleScreenViewModel> {
    override val values: Sequence<ScheduleScreenViewModel> = sequenceOf(
        FakeViewModel(),
    )

    class FakeViewModel : ScheduleScreenViewModel() {
        override val navigationAction: Flow<ScheduleScreenNavigationAction>
            get() = TODO("Not yet implemented")

        override val days: Flow<Days> = flowOf(
            Days(
                listOf(
                    Day(id = 1, name = "Monday"),
                    Day(id = 2, name = "Tuesday"),
                )
            )
        )

        override fun onClick(day: Day) {
        }

        override fun onBack() {
        }

    }
}
