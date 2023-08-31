package com.ilizma.schedule.data.datasource

import com.ilizma.schedule.data.model.Days

interface DaysDataSource {

    fun get(): Days

}