package com.ilizma.data.entity.schedule

class ScheduleResponseFactory {

    companion object {
        fun providesDataResponse(
            schedule: List<DataResponse.ScheduleResponse> = listOf(
                providesScheduleResponse(),
                providesScheduleResponse()
            )
        ) = DataResponse(
            schedule = schedule
        )

        fun providesScheduleResponse(
            hour: String = "10",
            day: String = "1",
            name: String = "Suelta la olla",
            repeated: String = "0"
        ) = DataResponse.ScheduleResponse(
            hour = hour,
            day = day,
            name = name,
            repeated = repeated
        )
    }

}