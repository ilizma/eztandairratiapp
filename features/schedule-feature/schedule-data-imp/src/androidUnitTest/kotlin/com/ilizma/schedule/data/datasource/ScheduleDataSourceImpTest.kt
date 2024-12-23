package com.ilizma.schedule.data.datasource

import com.ilizma.api.data.EztandaApi
import com.ilizma.api.model.ScheduleDTO
import com.ilizma.schedule.data.mapper.ScheduleStateDTOMapper
import com.ilizma.schedule.data.model.ScheduleState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleDataSourceImpTest {

    @RelaxedMockK
    private lateinit var api: EztandaApi

    @RelaxedMockK
    private lateinit var mapper: ScheduleStateDTOMapper

    @RelaxedMockK
    private lateinit var scheduleDTO: ScheduleDTO

    @RelaxedMockK
    private lateinit var scheduleState: ScheduleState

    private lateinit var dataSource: ScheduleDataSource

    init {
        MockKAnnotations.init(this)

        coEvery { api.getSchedule() } returns scheduleDTO
        every { mapper.from(scheduleDTO, any()) } returns scheduleState
    }

    @BeforeTest
    fun setup() {
        dataSource = ScheduleDataSourceImp(
            api = api,
            mapper = mapper,
            unknownError = "Unknown error",
        )
    }

    @Test
    fun `given api response, when get is called, then result is the expected`() = runTest {
        // given
        val expected = scheduleState

        // when
        val result = dataSource.get()

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given exception, when get is called, then result is the expected error`() = runTest {
        // given
        val expected = ScheduleState.Error("Unknown error")
        coEvery { api.getSchedule() } throws Exception()

        // when
        val result = dataSource.get()

        // then
        assertEquals(expected, result)
    }
}