package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.repository.PlayerRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class PlayerStopUseCaseImpTest {

    @RelaxedMockK
    private lateinit var repository: PlayerRepository

    private lateinit var useCase: PlayerStopUseCase

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        useCase = PlayerStopUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `when invoked, then repository stop should be executed`() {
        // when
        useCase()

        // then
        verify { repository.stop() }
    }

}