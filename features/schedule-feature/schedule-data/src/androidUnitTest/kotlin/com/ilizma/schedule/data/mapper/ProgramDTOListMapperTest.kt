package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ProgramDTO
import com.ilizma.schedule.data.model.Program
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramDTOListMapperTest {

    @RelaxedMockK
    private lateinit var programDTOMapper: ProgramDTOMapper

    @RelaxedMockK
    private lateinit var programDTO: ProgramDTO

    @RelaxedMockK
    private lateinit var program: Program

    private lateinit var mapper: ProgramDTOListMapper

    init {
        MockKAnnotations.init(this)

        every { programDTOMapper.from(programDTO) } returns program
    }

    @BeforeTest
    fun setup() {
        mapper = ProgramDTOListMapper(
            mapper = programDTOMapper,
        )
    }

    @Test
    fun `when from is called, then result is the expected`() {
        // given
        val programDTOList = listOf(programDTO)
        val expected = listOf(program)

        // when
        val result = mapper.from(programDTOList)

        // then
        assertEquals(expected, result)
    }

}