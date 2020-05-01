package com.ilizma.domain.entity.schedule

class Data(
    val schedule: List<Schedule>
) {

    class Schedule(
        val hour: String,
        val day: String,
        val name: String,
        val repeated: String
    )

}