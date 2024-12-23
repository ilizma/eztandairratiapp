package com.ilizma.schedule.presentation.mapper

import com.ilizma.schedule.domain.model.Program
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleStateMapperTest {

    @RelaxedMockK
    private lateinit var programTypeMapper: ProgramTypeMapper

    @RelaxedMockK
    private lateinit var program: Program

    @RelaxedMockK
    private lateinit var programTypeItem: ProgramType.Item

    private lateinit var mapper: ScheduleStateMapper

    init {
        MockKAnnotations.init(this)

        every { programTypeMapper.from(program) } returns programTypeItem
    }

    @BeforeTest
    fun setup() {
        mapper = ScheduleStateMapper(
            mapper = programTypeMapper,
        )
    }

    @Test
    fun `given title and error state, when from invoked, then result should be the expected ScheduleState`() {
        // given
        val title = "title"
        val state = ScheduleState.Error(
            message = "error"
        )
        val expected = PresentationScheduleState.Error(
            message = "error"
        )

        // when
        val result = mapper.from(title, state)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given title and state, when from invoked, then result should be the expected ScheduleState`() {
        // given
        val title = "title"
        val state = ScheduleState.Success(
            programList = listOf(program)
        )
        val expected = PresentationScheduleState.Success(
            title = title,
            list = listOf(programTypeItem)
        )

        // when
        val result = mapper.from(title, state)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given title and programList, when from invoked, then result should be the expected ScheduleState`() {
        // given
        val title = "title"
        val programList = listOf(program)
        val expected = PresentationScheduleState.Success(
            title = title,
            list = listOf(programTypeItem)
        )

        // when
        val result = mapper.from(title, programList)

        // then
        assertEquals(expected, result)
    }
}