package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ProgramDTO
import com.ilizma.schedule.data.model.Program
import kotlin.collections.map

class ProgramDTOListMapper(
    private val mapper: ProgramDTOMapper,
) {

    fun toData(
        dtoList: List<ProgramDTO>,
    ): List<Program> = dtoList.map { mapper.toData(it) }

}