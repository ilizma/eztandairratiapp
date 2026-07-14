package com.ilizma.schedule.data.datasource

class DayNameDataSource(
    private val dayName: () -> String,
) {

    fun get(): String = dayName()

}