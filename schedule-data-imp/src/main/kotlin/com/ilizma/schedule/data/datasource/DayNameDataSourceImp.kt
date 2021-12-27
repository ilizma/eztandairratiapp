package com.ilizma.schedule.data.datasource

class DayNameDataSourceImp(
    private val dayName: String, // (ScheduleDetailFragmentArgs by navArgs()).let { it.name }
) : DayNameDataSource {

    override fun get(): String = dayName

}