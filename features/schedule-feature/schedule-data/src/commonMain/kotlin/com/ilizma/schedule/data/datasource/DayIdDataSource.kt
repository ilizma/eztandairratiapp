package com.ilizma.schedule.data.datasource

class DayIdDataSource(
    private val dayId: () -> Int,
) {

    fun get(): Int = dayId()

}