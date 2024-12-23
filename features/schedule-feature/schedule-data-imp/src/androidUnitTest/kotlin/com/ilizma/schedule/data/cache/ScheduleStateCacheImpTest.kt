package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleState
import io.mockk.mockk
import org.junit.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ScheduleStateCacheImpTest {

    private lateinit var cache: ScheduleStateCache

    @BeforeTest
    fun setup() {
        cache = ScheduleStateCacheImp()
    }

    @Test
    fun `given non setted value, when get,then result should be null`() {
        // when
        val result = cache.get()

        // then
        assertNull(result)
    }

    @Test
    fun `given a setted value, when get,then result should be expected value`() {
        // given
        val scheduleDetailArgs = mockk<ScheduleState>()
        val expected = scheduleDetailArgs
        cache.set(scheduleDetailArgs)

        // when
        val result = cache.get()

        // then
        assertEquals(expected, result)
    }

}