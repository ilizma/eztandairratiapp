package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.cache.ScheduleDetailArgsCache
import com.ilizma.schedule.data.model.ScheduleDetailArgs
import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class ScheduleDetailArgsRepositoryImpTest {

    @RelaxedMockK
    private lateinit var cache: ScheduleDetailArgsCache

    private lateinit var repository: ScheduleDetailArgsRepository

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        repository = ScheduleDetailArgsRepositoryImp(
            cache = cache,
        )
    }

    @Test
    fun `given cache, when save, then cache should be set`() {
        // when
        repository.save(1, "name")

        // then
        verify { cache.set(ScheduleDetailArgs(1, "name")) }
    }
}