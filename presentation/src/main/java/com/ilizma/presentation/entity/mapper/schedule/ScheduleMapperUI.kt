package com.ilizma.presentation.entity.mapper.schedule

import com.ilizma.domain.entity.schedule.Data
import com.ilizma.presentation.entity.mapper.MapperUI

class ScheduleMapperUI : MapperUI<Data.Schedule, ScheduleUI> {

    override fun mapToUI(obj: Data.Schedule): ScheduleUI = ScheduleUI(
        hour = obj.hour,
        day = obj.day,
        name = obj.name,
        repeated = obj.repeated
    )

}