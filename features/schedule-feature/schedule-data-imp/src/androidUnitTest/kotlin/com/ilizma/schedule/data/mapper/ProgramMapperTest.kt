package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.data.model.Program as DataProgram
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramMapperTest {

    private lateinit var mapper: ProgramMapper

    @BeforeTest
    fun setup() {
        mapper = ProgramMapper()
    }

    @Test
    fun `given schedule, when from, then result should be the expected program`() {
        // given
        val schedule = DataProgram(
            hour = "hour",
            day = 1,
            name = "name",
            repeated = false
        )

        val expected = Program(
            hour = "hour",
            day = 1,
            name = "name",
            repeated = false
        )

        // when
        val result = mapper.from(schedule)

        // then
        assertEquals(expected, result)
    }
}