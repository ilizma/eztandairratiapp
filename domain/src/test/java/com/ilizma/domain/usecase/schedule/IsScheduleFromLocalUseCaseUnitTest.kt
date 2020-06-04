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
class IsScheduleFromLocalUseCaseUnitTest {

    private lateinit var isScheduleFromLocalUseCase: IsScheduleFromLocalUseCase

    @Mock
    private lateinit var scheduleRepository: ScheduleRepository

    @Before
    fun setUp() {
        isScheduleFromLocalUseCase = IsScheduleFromLocalUseCase(scheduleRepository)
    }

    @Test
    fun `invoke should return complete`() {
        whenever(scheduleRepository.isScheduleFromLocal())
            .doReturn(getCompletableComplete())

        val testObserver = isScheduleFromLocalUseCase(Unit).testAwait()

        testObserver.assertGeneralsCompletableSuccess()
    }

    @Test
    fun `invoke should emit a timeout`() {
        val timeout = Failure.Timeout("timeout")
        whenever(scheduleRepository.isScheduleFromLocal())
            .doReturn(getCompletableError(timeout))

        val testObserver = isScheduleFromLocalUseCase(Unit).testAwait()

        testObserver.assertGeneralsError {
            it == timeout
        }
    }

    @Test
    fun `invoke should emit an error`() {
        val error = Failure.Error("error")
        whenever(scheduleRepository.isScheduleFromLocal())
            .doReturn(getCompletableError(error))

        val testObserver = isScheduleFromLocalUseCase(Unit).testAwait()

        testObserver.assertGeneralsError {
            it == error
        }
    }

}