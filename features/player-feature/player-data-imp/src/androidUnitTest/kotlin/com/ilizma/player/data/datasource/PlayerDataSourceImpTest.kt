package com.ilizma.player.data.datasource

import com.ilizma.player.data.mapper.PlayerStateMapper
import com.ilizma.player.framework.PlayerFramework
import com.ilizma.player.data.model.PlayerState as DataPlayerState
import com.ilizma.player.framework.model.PlayerState as FrameworkPlayerState
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

class PlayerDataSourceImpTest {

    @RelaxedMockK
    private lateinit var frameworkPlayerState: FrameworkPlayerState

    @RelaxedMockK
    private lateinit var dataPlayerState: DataPlayerState

    @RelaxedMockK
    private lateinit var framework: PlayerFramework

    @RelaxedMockK
    private lateinit var mapper: PlayerStateMapper

    private lateinit var dataSource: PlayerDataSource

    init {
        MockKAnnotations.init(this)

        coEvery { framework.getState() } returns flowOf(frameworkPlayerState)
        every { mapper.from(frameworkPlayerState) } returns dataPlayerState
    }

    @BeforeTest
    fun setup() {
        dataSource = PlayerDataSourceImp(
            framework = framework,
            mapper = mapper,
        )
    }

    @Test
    fun `when getState, then result should be the expected`() = runTest {
        // given
        val expected = dataPlayerState

        // when
        val result = dataSource.getState()

        // then
        assertEquals(expected, result.last())
    }

    @Test
    fun `when play, then framework play should be called`() {
        // when
        dataSource.play()

        // then
        verify { framework.play() }
    }

    @Test
    fun `when stop, then framework stop should be called`() {
        // when
        dataSource.stop()

        // then
        verify { framework.stop() }
    }

}