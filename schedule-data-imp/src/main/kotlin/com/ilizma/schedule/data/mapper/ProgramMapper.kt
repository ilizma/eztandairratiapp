package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.data.model.Program as DataProgram

class ProgramMapper {

    fun toDomain(
        schedule: DataProgram,
    ): Program = Program(
        hour = schedule.hour,
        day = schedule.day,
        name = schedule.name,
        repeated = schedule.repeated
    )

}