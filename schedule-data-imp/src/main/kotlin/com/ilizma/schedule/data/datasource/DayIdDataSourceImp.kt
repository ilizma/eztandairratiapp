package com.ilizma.schedule.data.datasource

class DayIdDataSourceImp(
    private val dayId: Int, // (ScheduleDetailFragmentArgs by navArgs()).let { it.id }
) : DayIdDataSource {

    override fun get(): Int = dayId

}