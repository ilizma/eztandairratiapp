package com.ilizma.schedule.data.datasource

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DayNameDataSourceImpTest {

    private lateinit var dataSource: DayNameDataSource
    private val dayName = "Monday"

    @BeforeTest
    fun setup() {
        dataSource = DayNameDataSourceImp(
            dayName = { dayName },
        )
    }

    @Test
    fun `when get is called, then result is the expected`() {
        // given
        val expected = dayName

        // when
        val result = dataSource.get()

        // then
        assertEquals(expected, result)
    }
}