package com.ilizma.schedule.data.datasource

class DayNameDataSourceImp(
    private val dayName: String,
) : DayNameDataSource {

    override fun get(): String = dayName

}