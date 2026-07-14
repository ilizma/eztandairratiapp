package com.ilizma.schedule.data.mapper

import com.ilizma.api.model.ProgramDTO
import com.ilizma.api.model.ScheduleDTO
import com.ilizma.schedule.data.model.Program
import com.ilizma.schedule.data.model.ScheduleState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleStateDTOMapperTest {

    @RelaxedMockK
    private lateinit var programDTOListMapper: ProgramDTOListMapper

    @RelaxedMockK
    private lateinit var programDTO: ProgramDTO

    @RelaxedMockK
    private lateinit var program: Program

    private lateinit var mapper: ScheduleStateDTOMapper

    init {
        MockKAnnotations.init(this)

        every { programDTOListMapper.from(listOf(programDTO)) } returns listOf(program)
    }

    @BeforeTest
    fun setup() {
        mapper = ScheduleStateDTOMapper(
            mapper = programDTOListMapper,
        )
    }

    @Test
    fun `given ScheduleDTO, when from, then return Success ScheduleState`() {
        // given
        val unknownErrorMessage = "unknown error message"
        val scheduleDTO = ScheduleDTO(schedule = listOf(programDTO))
        val expected = ScheduleState.Success(listOf(program))

        // when
        val result = mapper.from(
            result = scheduleDTO,
            unknownErrorMessage = unknownErrorMessage,
        )

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given ScheduleDTO without schedule, when from, then return Error ScheduleState`() {
        // given
        val unknownErrorMessage = "unknown error message"
        val scheduleDTO = ScheduleDTO(schedule = null)
        val expected = ScheduleState.Error(unknownErrorMessage)

        // when
        val result = mapper.from(
            result = scheduleDTO,
            unknownErrorMessage = unknownErrorMessage,
        )

        // then
        assertEquals(expected, result)
    }
}