package com.ilizma.schedule.data.datasource

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DayIdDataSourceImpTest {

    private lateinit var dataSource: DayIdDataSource
    private val dayId = 1

    @BeforeTest
    fun setup() {
        dataSource = DayIdDataSourceImp(
            dayId = { dayId },
        )
    }

    @Test
    fun `when get is called, then result is the expected`() {
        // given
        val expected = dayId

        // when
        val result = dataSource.get()

        // then
        assertEquals(expected, result)
    }
}