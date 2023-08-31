package com.ilizma.schedule.data.mapper

import com.ilizma.navigation.domain.model.ScheduleDetailArgs
import com.ilizma.schedule.data.model.ScheduleDetailArgs as DataScheduleDetailArgs

class ScheduleDetailArgsMapper {

    fun from(
        args: ScheduleDetailArgs
    ): DataScheduleDetailArgs = DataScheduleDetailArgs(
        id = args.id,
        name = args.name,
    )
}