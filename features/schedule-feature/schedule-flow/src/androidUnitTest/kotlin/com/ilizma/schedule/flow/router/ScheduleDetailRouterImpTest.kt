package com.ilizma.schedule.flow.router

import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.navigator.ScheduleDetailCloseNavigator
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ScheduleDetailRouterImpTest {

    @RelaxedMockK
    private lateinit var closeNavigator: ScheduleDetailCloseNavigator

    @RelaxedMockK
    private lateinit var viewModel: ScheduleDetailScreenViewModel

    @RelaxedMockK
    private lateinit var navController: NavHostController

    private lateinit var router: ScheduleDetailRouter

    init {
        MockKAnnotations.init(this)
    }

    private fun setup(testDispatcher: TestDispatcher) {
        router = ScheduleDetailRouterImp(
            dispatcher = testDispatcher,
            closeNavigator = closeNavigator,
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
            navController = navController,
        )
        advanceUntilIdle()

        // then
        coVerify { viewModel.navigationAction.collect(any()) }
    }
}