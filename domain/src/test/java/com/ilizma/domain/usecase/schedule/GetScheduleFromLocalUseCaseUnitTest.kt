package com.ilizma.domain.usecase.schedule

import com.ilizma.domain.entity.base.Failure
import com.ilizma.domain.entity.schedule.ScheduleFactory.Companion.providesSchedule
import com.ilizma.domain.extensions.*
import com.ilizma.domain.repository.ScheduleRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetScheduleFromLocalUseCaseparamsTest {

    private lateinit var getScheduleFromLocalUseCase: GetScheduleFromLocalUseCase

    @Mock
    private lateinit var scheduleRepository: ScheduleRepository

    private val params = GetScheduleFromLocalParams(1)

    @Before
    fun setUp() {
        getScheduleFromLocalUseCase = GetScheduleFromLocalUseCase(scheduleRepository)
    }

    @Test
    fun `invoke should return complete`() {
        val scheduleList = listOf(providesSchedule(), providesSchedule())
        whenever(scheduleRepository.getScheduleFromLocal(params.day))
            .doReturn(getSingleSuccess(scheduleList))

        val testObserver = getScheduleFromLocalUseCase(params).testAwait()

        testObserver.assertGeneralsCompletableSuccess()
    }

    @Test
    fun `invoke should emit a timeout`() {
        val timeout = Failure.Timeout("timeout")
        whenever(scheduleRepository.getScheduleFromLocal(params.day))
            .doReturn(getSingleError(timeout))

        val testObserver = getScheduleFromLocalUseCase(params).testAwait()

        testObserver.assertGeneralsError {
            it == timeout
        }
    }

    @Test
    fun `invoke should emit an error`() {
        val error = Failure.Error("error")
        whenever(scheduleRepository.getScheduleFromLocal(params.day))
            .doReturn(getSingleError(error))

        val testObserver = getScheduleFromLocalUseCase(params).testAwait()

        testObserver.assertGeneralsError {
            it == error
        }
    }

}