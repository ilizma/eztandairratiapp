package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.presentation.model.Day
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DayMapperTest {

    private lateinit var mapper: DayMapper

    @BeforeTest
    fun setup() {
        mapper = DayMapper()
    }

    @Test
    fun `given index and day, when from invoked, then result should be the expected Day`() {
        // given
        val index = 0
        val day = "Monday"
        val expected = Day(
            id = 1,
            name = "Monday"
        )

        // when
        val result = mapper.from(index, day)

        // then
        assertEquals(expected, result)
    }
}