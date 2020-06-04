package com.ilizma.domain.entity.schedule

class ScheduleFactory {

    companion object {

        fun providesSchedule(
            hour: String = "10",
            day: String = "1",
            name: String = "Suelta la olla",
            repeated: String = "0"
        ) = Schedule(
            hour = hour,
            day = day,
            name = name,
            repeated = repeated
        )
    }

}