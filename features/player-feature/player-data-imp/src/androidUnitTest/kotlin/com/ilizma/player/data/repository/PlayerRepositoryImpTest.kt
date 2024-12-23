package com.ilizma.player.data.repository

import com.ilizma.player.data.datasource.PlayerDataSource
import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.domain.repository.PlayerRepository
import com.ilizma.player.data.model.PlayerState as DataPlayerState
import com.ilizma.player.domain.model.PlayerState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class PlayerRepositoryImpTest {

    @RelaxedMockK
    private lateinit var dataPlayerState: DataPlayerState

    @RelaxedMockK
    private lateinit var playerState: PlayerState

    @RelaxedMockK
    private lateinit var dataSource: PlayerDataSource

    @RelaxedMockK
    private lateinit var mapper: PlayerStateMapper

    private lateinit var repository: PlayerRepository

    init {
        MockKAnnotations.init(this)

        coEvery { dataSource.getState() } returns flowOf(dataPlayerState)
        every { mapper.from(dataPlayerState) } returns playerState
    }

    @BeforeTest
    fun setup() {
        repository = PlayerRepositoryImp(
            dataSource = dataSource,
            mapper = mapper,
        )
    }

    @Test
    fun `when getState, then result should be the expected`() = runTest {
        // given
        val expected = playerState

        // when
        val result = repository.getState()

        // then
        assertEquals(expected, result.last())
    }

    @Test
    fun `when play, then dataSource play should be called`() {
        // when
        repository.play()

        // then
        verify { dataSource.play() }
    }

    @Test
    fun `when stop, then dataSource stop should be called`() {
        // when
        repository.stop()

        // then
        verify { dataSource.stop() }
    }

}