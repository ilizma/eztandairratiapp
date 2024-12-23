package com.ilizma.schedule.data.mapper

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import com.ilizma.api.model.ProgramDTO
import com.ilizma.schedule.data.model.Program

class ProgramDTOMapper(
    private val hourString: String,
) {

    fun from(
        dto: ProgramDTO,
    ): Program = Program(
        hour = NSString.stringWithFormat(hourString, (dto.hour ?: "") as NSString),
        day = dto.day ?: -1,
        name = dto.name ?: "",
        repeated = when (dto.repeated) {
            1 -> true
            else -> false
        },
    )

}