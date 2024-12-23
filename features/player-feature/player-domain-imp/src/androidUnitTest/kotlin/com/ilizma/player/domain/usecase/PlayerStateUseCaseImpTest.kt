package com.ilizma.player.domain.usecase

import com.ilizma.player.domain.model.PlayerState
import com.ilizma.player.domain.repository.PlayerRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PlayerStateUseCaseImpTest {

    @RelaxedMockK
    private lateinit var playerState: PlayerState

    @RelaxedMockK
    private lateinit var repository: PlayerRepository

    private lateinit var useCase: PlayerStateUseCase

    init {
        MockKAnnotations.init(this)

        coEvery { repository.getState() } returns flowOf(playerState)
    }

    @BeforeTest
    fun setup() {
        useCase = PlayerStateUseCaseImp(
            repository = repository,
        )
    }

    @Test
    fun `when invoked, then result should be the expected`() = runTest {
        // given
        val expected = playerState

        // when
        val result = useCase()

        // then
        assertEquals(expected, result.first())
    }

}