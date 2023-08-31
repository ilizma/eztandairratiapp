package com.ilizma.navigation.presentation.mapper

import com.ilizma.navigation.domain.model.ScheduleDetailArgs
import com.ilizma.navigation.presentation.model.ScheduleDetailArgs as PresentationScheduleDetailArgs

class ScheduleDetailArgsMapper {

    fun from(
        args: PresentationScheduleDetailArgs
    ): ScheduleDetailArgs = ScheduleDetailArgs(
        id = args.id,
        name = args.name,
    )
}