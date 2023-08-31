package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.presentation.model.ProgramType

class ProgramTypeMapper {

    fun from(
        program: Program,
    ): ProgramType.Item = ProgramType.Item(
        hour = program.hour,
        day = program.day,
        name = program.name,
        repeated = program.repeated,
    )

}