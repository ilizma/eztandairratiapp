package com.ilizma.schedule.presentation.model

sealed class ScheduleState(
    open val list: List<ProgramType>,
    open val dayId: Int,
) {

    data class Loading(
        override val dayId: Int = -1,
        override val list: List<ProgramType.Loading>,
    ) : ScheduleState(
        dayId = dayId,
        list = list,
    )

    data class Success(
        override val dayId: Int,
        override val list: List<ProgramType.Item>,
        val title: String,
    ) : ScheduleState(
        dayId = dayId,
        list = list,
    )

    data class Error(
        override val dayId: Int,
        val message: String,
    ) : ScheduleState(
        dayId = dayId,
        list = emptyList(),
    )

}