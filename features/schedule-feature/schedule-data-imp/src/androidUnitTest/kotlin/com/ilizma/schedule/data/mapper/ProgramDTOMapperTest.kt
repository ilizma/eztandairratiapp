package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ProgramDTO
import com.ilizma.schedule.data.model.Program
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class ProgramDTOMapperTest {

    private lateinit var mapper: ProgramDTOMapper

    @BeforeTest
    fun setup() {
        mapper = ProgramDTOMapper(
            hourString = "%s:00",
        )
    }

    @Test
    fun `when from is called, then result is the expected`() {
        // given
        val expected = Program(
            name = "name",
            hour = "1:00",
            day = 1,
            repeated = false,
        )

        // when
        val result = mapper.from(
            ProgramDTO(
                name = "name",
                hour = "1",
                day = 1,
            )
        )

        // then
        assertEquals(expected, result)
    }

}