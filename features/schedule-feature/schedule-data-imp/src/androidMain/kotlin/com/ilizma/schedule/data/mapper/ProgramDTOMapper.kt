package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ProgramDTO
import com.ilizma.schedule.data.model.Program

class ProgramDTOMapper(
    private val hourString: String,
) {

    fun from(
        dto: ProgramDTO,
    ): Program = Program(
        hour = hourString.format(dto.hour ?: ""),
        day = dto.day ?: -1,
        name = dto.name ?: "",
        repeated = when (dto.repeated) {
            1 -> true
            else -> false
        },
    )

}