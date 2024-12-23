package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.data.model.Program as DataProgram
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProgramListMapperTest {

    @RelaxedMockK
    private lateinit var programMapper: ProgramMapper

    @RelaxedMockK
    private lateinit var dataProgram: DataProgram

    @RelaxedMockK
    private lateinit var program: Program

    private lateinit var mapper: ProgramListMapper

    init {
        MockKAnnotations.init(this)

        every { programMapper.from(dataProgram) } returns program
        every { dataProgram.day } returns 1
        every { dataProgram.hour } returns "1:00"
    }

    @BeforeTest
    fun setup() {
        mapper = ProgramListMapper(
            mapper = programMapper,
        )
    }

    @Test
    fun `given programList, when from, then return expected programList`() {
        // given
        val dayId = 1
        val programList = listOf(dataProgram)
        val expected = listOf(program)

        // when
        val result = mapper.from(
            programList = programList,
            dayId = dayId,
        )

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given messy programList, when from, then return expected sorted programList`() {
        // given
        val dayId = 1
        val dataProgram = DataProgram(
            day = 1,
            hour = "2:00",
            name = "",
            repeated = false
        )
        val programList = listOf(
            dataProgram, dataProgram.copy(hour = "1:00"),
        )
        val program = Program(
            day = 1,
            hour = "1:00",
            name = "",
            repeated = false
        )
        val expected = listOf(program, program.copy(hour = "2:00"))
        every { programMapper.from(dataProgram) } returns program.copy(hour = "2:00")
        every { programMapper.from(dataProgram.copy(hour = "1:00")) } returns program

        // when
        val result = mapper.from(
            programList = programList,
            dayId = dayId,
        )

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given programList with different dayId, when from, then return empty programList`() {
        // given
        val dayId = 1
        val programList = listOf(dataProgram, dataProgram)
        val expected = listOf(program, program)

        // when
        val result = mapper.from(
            programList = programList,
            dayId = dayId,
        )

        // then
        assertEquals(expected, result)
    }
}