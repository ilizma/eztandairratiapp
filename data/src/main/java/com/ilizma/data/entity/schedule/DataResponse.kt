package com.ilizma.data.entity.schedule

import com.ilizma.domain.entity.base.ResponseObject
import com.ilizma.domain.entity.schedule.Data
import com.squareup.moshi.Json

class DataResponse(
    @Json(name = "data") val schedule: List<ScheduleResponse>
) : ResponseObject<Data> {

    override fun toDomain(): Data = Data(
        schedule = schedule.map { it.toDomain() }
    )

    class ScheduleResponse(
        @Json(name = "hora") val hour: String,
        @Json(name = "dia") val day: String,
        @Json(name = "nombre") val name: String,
        @Json(name = "repeticion") val repeated: String
    ) : ResponseObject<Data.Schedule> {

        override fun toDomain(): Data.Schedule = Data.Schedule(
            hour = hour,
            day = day,
            name = name,
            repeated = repeated
        )
    }

}