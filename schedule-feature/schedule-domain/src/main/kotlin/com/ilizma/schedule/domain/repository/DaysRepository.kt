package com.ilizma.schedule.domain.repository

import com.ilizma.schedule.domain.model.Days

interface DaysRepository {

    fun get(): Days

}