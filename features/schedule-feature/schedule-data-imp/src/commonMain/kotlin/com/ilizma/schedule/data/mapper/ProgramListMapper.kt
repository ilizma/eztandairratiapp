package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.data.model.Program as DataProgram

class ProgramListMapper(
    private val mapper: ProgramMapper,
) {

    fun from(
        programList: List<DataProgram>,
        dayId: Int,
    ): List<Program> = programList
        .filter { it.day == dayId }
        .sortedBy { it.hour.split(":").first().toInt() }
        .map { mapper.from(it) }

}