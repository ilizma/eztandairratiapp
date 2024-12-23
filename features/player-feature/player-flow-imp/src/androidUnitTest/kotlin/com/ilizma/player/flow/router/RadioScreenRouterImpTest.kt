package com.ilizma.player.flow.router

import com.ilizma.cast.flow.navigator.CastPlayerNavigator
import com.ilizma.player.flow.navigator.RadioCloseNavigator
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class RadioScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var radioCloseNavigator: RadioCloseNavigator

    @RelaxedMockK
    private lateinit var castPlayerNavigator: CastPlayerNavigator

    @RelaxedMockK
    private lateinit var viewModel: RadioScreenViewModel

    private lateinit var router: RadioScreenRouter

    init {
        MockKAnnotations.init(this)
    }

    private fun setup(testDispatcher: TestDispatcher) {
        router = RadioScreenRouterImp(
            dispatcher = testDispatcher,
            radioCloseNavigator = radioCloseNavigator,
            castPlayerNavigator = castPlayerNavigator,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when router is initialized, then viewModel functions are called`() = runTest {
        // given
        val testDispatcher = StandardTestDispatcher(testScheduler)
        setup(testDispatcher)

        // when
        router.init(
            coroutineScope = CoroutineScope(testDispatcher),
            viewModel = viewModel,
        )
        advanceUntilIdle()

        // then
        coVerify { viewModel.navigationAction.collect(any()) }
    }

}