package com.ilizma.schedule.data.repository

import com.ilizma.schedule.data.datasource.DayNameDataSource
import com.ilizma.schedule.domain.repository.DayNameRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DayNameRepositoryImpTest {

    @RelaxedMockK
    private lateinit var dataSource: DayNameDataSource

    private lateinit var repository: DayNameRepository

    init {
        MockKAnnotations.init(this)

        every { dataSource.get() } returns "Monday"
    }

    @BeforeTest
    fun setup() {
        repository = DayNameRepositoryImp(
            dataSource = dataSource,
        )
    }

    @Test
    fun `given dataSource, when get, then result should be the expected dayName`() {
        // given
        val expected = "Monday"

        // when
        val result = repository.get()

        // then
        assertEquals(expected, result)
    }
}