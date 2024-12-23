package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.domain.repository.ScheduleRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: ScheduleRepository

    @RelaxedMockK
    private lateinit var scheduleState: ScheduleState

    private lateinit var useCase: ScheduleUseCase

    init {
        MockKAnnotations.init(this)

        coEvery { repository.get() } returns scheduleState
    }

    @BeforeTest
    fun setup() {
        useCase = ScheduleUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `given repository, when invoked, then result should be the expected scheduleState`() =
        runTest {
            // given
            val expected = scheduleState

            // when
            val result = useCase()

            // then
            assertEquals(expected, result)
        }
}