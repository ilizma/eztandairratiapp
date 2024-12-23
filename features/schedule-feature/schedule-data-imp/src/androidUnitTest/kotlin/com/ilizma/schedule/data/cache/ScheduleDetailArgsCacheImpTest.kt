package com.ilizma.schedule.data.cache

import com.ilizma.schedule.data.model.ScheduleDetailArgs
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ScheduleDetailArgsCacheImpTest {

    private lateinit var cache: ScheduleDetailArgsCache

    @BeforeTest
    fun setup() {
        cache = ScheduleDetailArgsCacheImp()
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
        val scheduleDetailArgs = mockk<ScheduleDetailArgs>()
        val expected = scheduleDetailArgs
        cache.set(scheduleDetailArgs)

        // when
        val result = cache.get()

        // then
        assertEquals(expected, result)
    }
}