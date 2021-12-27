package com.ilizma.schedule.flow.mapper

import com.ilizma.schedule.flow.model.Day as FlowDay
import com.ilizma.schedule.presentation.model.Day as PresentationDay

class DayMapper {

    fun toFlow(
        day: PresentationDay,
    ): FlowDay = FlowDay(
        id = day.id,
        name = day.name
    )

}