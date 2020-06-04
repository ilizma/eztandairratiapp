package com.ilizma.domain.usecase.schedule

import com.ilizma.domain.entity.base.Failure
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
class GetGetScheduleUseCaseUnitTest {

    private lateinit var getScheduleUseCase: GetScheduleUseCase

    @Mock
    private lateinit var scheduleRepository: ScheduleRepository

    @Before
    fun setUp() {
        getScheduleUseCase = GetScheduleUseCase(scheduleRepository)
    }

    @Test
    fun `invoke should return complete`() {
        whenever(scheduleRepository.getSchedule())
            .doReturn(getCompletableComplete())

        val testObserver = getScheduleUseCase(Unit).testAwait()

        testObserver.assertGeneralsCompletableSuccess()
    }

    @Test
    fun `invoke should emit a timeout`() {
        val timeout = Failure.Timeout("timeout")
        whenever(scheduleRepository.getSchedule())
            .doReturn(getCompletableError(timeout))

        val testObserver = getScheduleUseCase(Unit).testAwait()

        testObserver.assertGeneralsError {
            it == timeout
        }
    }

    @Test
    fun `invoke should emit an error`() {
        val error = Failure.Error("error")
        whenever(scheduleRepository.getSchedule())
            .doReturn(getCompletableError(error))

        val testObserver = getScheduleUseCase(Unit).testAwait()

        testObserver.assertGeneralsError {
            it == error
        }
    }

}