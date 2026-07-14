package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.presentation.model.ProgramType
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramTypeMapperTest {

    private lateinit var mapper: ProgramTypeMapper

    @BeforeTest
    fun setup() {
        mapper = ProgramTypeMapper()
    }

    @Test
    fun `given program, when from invoked, then result should be the expected ProgramType Item`() {
        // given
        val program = Program(
            hour = "10:00",
            day = 1,
            name = "Test",
            repeated = false,
        )
        val expected = ProgramType.Item(
            hour = "10:00",
            day = 1,
            name = "Test",
            repeated = false,
        )

        // when
        val result = mapper.from(program)

        // then
        assertEquals(expected, result)
    }
}