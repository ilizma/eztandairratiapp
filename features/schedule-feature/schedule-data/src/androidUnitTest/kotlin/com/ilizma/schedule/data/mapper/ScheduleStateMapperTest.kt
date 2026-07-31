package com.ilizma.schedule.data.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.data.model.ScheduleState as DataScheduleState
import com.ilizma.schedule.data.model.Program as ProgramData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleStateMapperTest {

    @RelaxedMockK
    private lateinit var programListMapper: ProgramListMapper

    @RelaxedMockK
    private lateinit var programData: ProgramData

    @RelaxedMockK
    private lateinit var program: Program

    private lateinit var mapper: ScheduleStateMapper

    init {
        MockKAnnotations.init(this)

        every { programListMapper.from(listOf(programData), any()) } returns listOf(program)
    }

    @BeforeTest
    fun setup() {
        mapper = ScheduleStateMapper(programListMapper)
    }

    @Test
    fun `given DataScheduleState Error, when from invoked, then result should be ScheduleState Error`() {
        // given
        val dataScheduleState = DataScheduleState.Error("error")
        val expected = ScheduleState.Error("error")

        // when
        val result = mapper.from(dataScheduleState, 0)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given DataScheduleState Success, when from invoked, then result should be ScheduleState Success`() {
        // given
        val dataScheduleState = DataScheduleState.Success(listOf(programData))
        val expected = ScheduleState.Success(listOf(program))

        // when
        val result = mapper.from(dataScheduleState, 0)

        // then
        assertEquals(expected, result)
    }
}