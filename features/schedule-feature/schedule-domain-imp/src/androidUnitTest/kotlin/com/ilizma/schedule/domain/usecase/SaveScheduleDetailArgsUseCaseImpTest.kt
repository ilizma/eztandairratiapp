package com.ilizma.schedule.domain.usecase

import com.ilizma.schedule.domain.repository.ScheduleDetailArgsRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class SaveScheduleDetailArgsUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: ScheduleDetailArgsRepository

    private lateinit var useCase: SaveScheduleDetailArgsUseCase

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        useCase = SaveScheduleDetailArgsUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `given repository, when invoked, then save should be invoked`() {
        // given
        val id = 1
        val name = "Monday"

        // when
        useCase(id, name)

        // then
        verify { repository.save(id, name) }
    }
}