package com.ilizma.schedule.data.datasource

class DayIdDataSourceImp(
    private val dayId: () -> Int,
) : DayIdDataSource {

    override fun get(): Int = dayId()

}