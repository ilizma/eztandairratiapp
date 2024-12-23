package com.ilizma.schedule.presentation.viewmodel

import com.ilizma.schedule.domain.model.ScheduleState
import com.ilizma.schedule.presentation.model.ScheduleState as PresentationScheduleState
import com.ilizma.schedule.domain.usecase.DayNameUseCase
import com.ilizma.schedule.domain.usecase.SaveScheduleDetailArgsUseCase
import com.ilizma.schedule.domain.usecase.ScheduleUseCase
import com.ilizma.schedule.presentation.mapper.ScheduleStateMapper
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleDetailScreenViewModelImpTest {

    @RelaxedMockK
    private lateinit var saveCacheUseCase: SaveScheduleDetailArgsUseCase

    @RelaxedMockK
    private lateinit var dayNameUseCase: DayNameUseCase

    @RelaxedMockK
    private lateinit var scheduleUseCase: ScheduleUseCase

    @RelaxedMockK
    private lateinit var mapper: ScheduleStateMapper

    private lateinit var viewModel: ScheduleDetailScreenViewModel

    init {
        MockKAnnotations.init(this)
    }

    private fun setup(testDispatcher: TestDispatcher) {
        viewModel = ScheduleDetailScreenViewModelImp(
            dispatcher = testDispatcher,
            saveCacheUseCase = saveCacheUseCase,
            dayNameUseCase = dayNameUseCase,
            scheduleUseCase = scheduleUseCase,
            mapper = mapper,
            isDebug = true,
            _scheduleState = MutableSharedFlow(),
            _navigationAction = MutableSharedFlow(),
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `given intent SaveCache, when onIntent invoked, then saveCache should be called`() =
        runTest {
            // given
            val id = 1
            val name = "name"
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            setup(testDispatcher)

            // when
            viewModel.onIntent(ScheduleDetailScreenIntent.SaveCache(id = id, name = name))

            // then
            coVerify { saveCacheUseCase(id, name) }
        }

    @Test
    fun `given intent GetSchedule, when onIntent invoked, then scheduleState should be the expected`() =
        runTest {
            // given
            val state = mockk<ScheduleState>()
            val dayName = "Monday"
            val presentationState = mockk<PresentationScheduleState>()
            val testDispatcher = StandardTestDispatcher(testScheduler)
            val expected = presentationState
            setup(testDispatcher)
            coEvery { scheduleUseCase() } returns state
            coEvery { dayNameUseCase() } returns dayName
            every { mapper.from(dayName, state) } returns presentationState

            // when
            viewModel.onIntent(ScheduleDetailScreenIntent.GetSchedule)

            // then
            backgroundScope.launch(testDispatcher) {
                // then
                assertEquals(expected, viewModel.scheduleState.last())
            }
        }

    @Test
    fun `given intent RetrySchedule, when onIntent invoked, then scheduleState should be the expected`() =
        runTest {
            // given
            val state = mockk<ScheduleState>()
            val dayName = "Monday"
            val presentationState = mockk<PresentationScheduleState>()
            val testDispatcher = StandardTestDispatcher(testScheduler)
            val expected = presentationState
            setup(testDispatcher)
            coEvery { scheduleUseCase() } returns state
            coEvery { dayNameUseCase() } returns dayName
            every { mapper.from(dayName, state) } returns presentationState

            // when
            viewModel.onIntent(ScheduleDetailScreenIntent.RetrySchedule)

            // then
            backgroundScope.launch(testDispatcher) {
                // then
                assertEquals(expected, viewModel.scheduleState.last())
            }
        }

    @Test
    fun `given intent Back, when onIntent invoked, then navigationAction should be the expected`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = ScheduleDetailNavigationAction.Back

            // when
            viewModel.onIntent(ScheduleDetailScreenIntent.Back)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

}