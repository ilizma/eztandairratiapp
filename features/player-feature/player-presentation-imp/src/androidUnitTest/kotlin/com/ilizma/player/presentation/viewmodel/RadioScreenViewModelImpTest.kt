package com.ilizma.player.presentation.viewmodel

import com.ilizma.cast.framework.CastFramework
import com.ilizma.cast.framework.model.CastState
import com.ilizma.player.domain.usecase.PlayerPlayUseCase
import com.ilizma.player.domain.usecase.PlayerStateUseCase
import com.ilizma.player.domain.usecase.PlayerStopUseCase
import com.ilizma.player.presentation.mapper.PlayerStateMapper
import com.ilizma.player.presentation.model.RadioScreenIntent
import com.ilizma.player.presentation.model.RadioScreenNavigationAction
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class RadioScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var stateUseCase: PlayerStateUseCase

    @RelaxedMockK
    private lateinit var playUseCase: PlayerPlayUseCase

    @RelaxedMockK
    private lateinit var stopUseCase: PlayerStopUseCase

    @RelaxedMockK
    private lateinit var castFramework: CastFramework

    @RelaxedMockK
    private lateinit var mapper: PlayerStateMapper

    private lateinit var viewModel: RadioScreenViewModel

    init {
        MockKAnnotations.init(this)
    }

    private fun setup(testDispatcher: TestDispatcher) {
        viewModel = RadioScreenViewModelImp(
            ioDispatcher = testDispatcher,
            mainDispatcher = testDispatcher,
            stateUseCase = stateUseCase,
            playUseCase = playUseCase,
            stopUseCase = stopUseCase,
            castFramework = castFramework,
            mapper = mapper,
            _navigationAction = MutableSharedFlow(),
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given Play RadioScreenIntent, when onIntent, then playUseCase should be executed`() =
        runTest {
            // given
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            coEvery { castFramework.castState } returns flowOf(CastState.DISCONNECTED)
            setup(testDispatcher)

            // when
            viewModel.onIntent(RadioScreenIntent.Play)

            // then
            verify { playUseCase() }
        }

    @Test
    fun `given Stop RadioScreenIntent, when onIntent, then stopUseCase should be executed`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)

            // when
            viewModel.onIntent(RadioScreenIntent.Stop)

            // then
            verify { stopUseCase() }
        }

    @Test
    fun `given Back RadioScreenIntent, when onIntent, then stopUseCase should be executed`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = RadioScreenNavigationAction.Back

            // when
            viewModel.onIntent(RadioScreenIntent.Back)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

}