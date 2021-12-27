package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ProgramDTO
import com.ilizma.schedule.data.model.Program

class ProgramDTOMapper(
    private val hourString: String,//R.string.hour
) {

    fun toData(
        dto: ProgramDTO,
    ): Program = Program(
        hour = String.format(hourString, dto.hour ?: ""),
        day = dto.day ?: -1,
        name = dto.name ?: "",
        repeated = dto.repeated ?: false,
    )

}