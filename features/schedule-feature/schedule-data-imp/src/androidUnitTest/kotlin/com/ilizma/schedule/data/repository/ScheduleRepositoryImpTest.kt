package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.cache.ScheduleStateCache
import com.ilizma.schedule.data.datasource.DayIdDataSource
import com.ilizma.schedule.data.datasource.ScheduleDataSource
import com.ilizma.schedule.data.mapper.ScheduleStateMapper
import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.data.model.ScheduleState as DataScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleRepositoryImpTest {

    @RelaxedMockK
    private lateinit var dataSource: ScheduleDataSource

    @RelaxedMockK
    private lateinit var dayIdDataSource: DayIdDataSource

    @RelaxedMockK
    private lateinit var cache: ScheduleStateCache

    @RelaxedMockK
    private lateinit var mapper: ScheduleStateMapper

    @RelaxedMockK
    private lateinit var dataScheduleState: DataScheduleState

    @RelaxedMockK
    private lateinit var scheduleState: ScheduleState

    private lateinit var repository: ScheduleRepository

    init {
        MockKAnnotations.init(this)

        every { dayIdDataSource.get() } returns 1
        every { mapper.from(dataScheduleState, any()) } returns scheduleState
    }

    @BeforeTest
    fun setup() {
        repository = ScheduleRepositoryImp(
            dataSource = dataSource,
            dayIdDataSource = dayIdDataSource,
            cache = cache,
            mapper = mapper,
        )
    }

    @Test
    fun `given empty cache, when get, then result should be the expected and cache should be set`() = runTest {
        // given
        val expected = scheduleState
        every { cache.get() } returns null
        coEvery { dataSource.get() } returns dataScheduleState

        // when
        val result = repository.get()

        // then
        verify { cache.set(dataScheduleState) }
        assertEquals(expected, result)
    }

    @Test
    fun `given cache, when get, then result should be the expected`() = runTest {
        // given
        val expected = scheduleState
        every { cache.get() } returns dataScheduleState

        // when
        val result = repository.get()

        // then
        assertEquals(expected, result)
    }
}