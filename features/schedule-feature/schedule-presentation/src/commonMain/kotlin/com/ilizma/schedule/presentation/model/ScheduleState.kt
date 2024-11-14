package com.ilizma.schedule.presentation.model

sealed class ScheduleState(
    open val list: List<ProgramType>,
) {
    data class Loading(
        override val list: List<ProgramType.Loading>,
    ) : ScheduleState(
        list,
    )

    data class Success(
        val title: String,
        override val list: List<ProgramType.Item>,
    ) : ScheduleState(
        list,
    )

    data class Error(
        val message: String,
    ) : ScheduleState(
        listOf(),
    )

}