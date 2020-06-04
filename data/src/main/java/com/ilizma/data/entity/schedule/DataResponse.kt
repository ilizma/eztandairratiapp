package com.ilizma.data.entity.schedule

import com.ilizma.domain.entity.base.ResponseObject
import com.ilizma.domain.entity.schedule.Schedule
import com.squareup.moshi.Json

class DataResponse(
    @Json(name = "data") val schedule: List<ScheduleResponse>
) : ResponseObject<List<Schedule>> {

    override fun toDomain(): List<Schedule> = schedule.map { it.toDomain() }

    class ScheduleResponse(
        @Json(name = "hora") val hour: String,
        @Json(name = "dia") val day: String,
        @Json(name = "nombre") val name: String,
        @Json(name = "repeticion") val repeated: String
    ) : ResponseObject<Schedule> {

        override fun toDomain(): Schedule = Schedule(
            hour = hour,
            day = day,
            name = name,
            repeated = repeated
        )
    }

}