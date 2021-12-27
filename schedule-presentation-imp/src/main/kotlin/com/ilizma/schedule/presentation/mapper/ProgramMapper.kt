package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.presentation.model.Program as PresentationProgram

class ProgramMapper {

    fun toPresentation(
        program: Program,
    ): PresentationProgram = PresentationProgram(
        hour = program.hour,
        day = program.day,
        name = program.name,
        repeated = program.repeated,
    )

}