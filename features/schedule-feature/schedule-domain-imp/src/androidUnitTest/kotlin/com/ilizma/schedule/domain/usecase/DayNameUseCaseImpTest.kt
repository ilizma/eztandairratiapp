package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.repository.DayNameRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DayNameUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: DayNameRepository

    private lateinit var useCase: DayNameUseCase

    init {
        MockKAnnotations.init(this)

        every { repository.get() } returns "Monday"
    }

    @BeforeTest
    fun setup() {
        useCase = DayNameUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `given repository, when invoked, then result should be the expected dayName`() {
        // given
        val expected = "Monday"

        // when
        val result = useCase()

        // then
        assertEquals(expected, result)
    }
}