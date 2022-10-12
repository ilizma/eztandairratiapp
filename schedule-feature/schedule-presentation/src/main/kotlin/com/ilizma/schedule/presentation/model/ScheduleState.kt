package com.ilizma.schedule.presentation.model

sealed class ScheduleState(
    open val list: List<ProgramType>,
) {
    data class Success(
        override val list: List<ProgramType.Item>,
    ) : ScheduleState(
        list,
    )

    data class Loading(
        override val list: List<ProgramType.Loading>,
    ) : ScheduleState(
        list,
    )

}