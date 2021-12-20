package com.ilizma.presentation.entity.mapper.schedule

import com.ilizma.domain.entity.schedule.Schedule
import com.ilizma.presentation.entity.mapper.MapperUI

class ScheduleMapperUI : MapperUI<Schedule, ScheduleUI> {

    override fun mapToUI(
        obj: Schedule,
    ): ScheduleUI = ScheduleUI(
        hour = obj.hour,
        day = obj.day,
        name = obj.name,
        repeated = obj.repeated
    )

}