package com.ilizma.schedule.view.utils

import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class ScheduleDetailScreenPreviewProvider :
    PreviewParameterProvider<ScheduleState> {
    override val values: Sequence<ScheduleState> = sequenceOf(
        listOf(
            ProgramType.Loading,
            ProgramType.Loading,
        ).let {
            ScheduleState.Loading(
                dayId = 1,
                list = it,
            )
        },
        ScheduleState.Error(
            dayId = 1,
            message = "Error",
        ),
        listOf(
            ProgramType.Item(
                hour = "12:00",
                day = 1,
                name = "Program 1",
                repeated = false,
            ),
            ProgramType.Item(
                hour = "13:00",
                day = 1,
                name = "Program 2",
                repeated = true,
            ),
        ).let {
            ScheduleState.Success(
                title = "Monday",
                dayId = 1,
                list = it,
            )
        },
        ScheduleState.Success(
            title = "Monday",
            dayId = 1,
            list = listOf(),
        ),
    )
}