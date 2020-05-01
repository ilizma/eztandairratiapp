package com.ilizma.data.repository.schedule

import com.ilizma.data.entity.schedule.ScheduleFactory.Companion.providesData
import com.ilizma.data.extensions.*
import com.ilizma.data.repository.schedule.datasources.ScheduleLocalDataSource
import com.ilizma.data.repository.schedule.datasources.ScheduleRemoteDataSource
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ScheduleRepositoryImplUnitTest {

    private lateinit var scheduleRepositoryImpl: ScheduleRepositoryImpl

    @Mock
    private lateinit var scheduleRemoteDataSource: ScheduleRemoteDataSource

    @Mock
    private lateinit var scheduleLocalDataSource: ScheduleLocalDataSource

    @Before
    fun setUp() {
        scheduleRepositoryImpl =
            ScheduleRepositoryImpl(scheduleRemoteDataSource, scheduleLocalDataSource)
    }

    // region Success cases
    @Test
    fun `getSchedule should return data single save it`() {
        val data = providesData()
        whenever(scheduleRemoteDataSource.getSchedule())
            .doReturn(getSingleSuccess(data))

        val testObserver = scheduleRepositoryImpl.getSchedule().test()

        testObserver.assertGeneralsCompletableSuccess()
        verify(scheduleLocalDataSource).saveSchedule(data.schedule)
    }

    @Test
    fun `nuke should return Completable`() {
        whenever(scheduleLocalDataSource.nuke())
            .doReturn(getCompletableComplete())

        val testObserver = scheduleRepositoryImpl.nuke().test()

        testObserver.assertGeneralsCompletableSuccess()
    }
    // endregion

    // region Failure cases
    @Test
    fun `getSchedule should return Failure and don't save it`() {
        whenever(scheduleRemoteDataSource.getSchedule())
            .doReturn(getSingleError())

        val testObserver = scheduleRepositoryImpl.getSchedule().test()

        testObserver.assertGeneralsError()
        verifyZeroInteractions(scheduleLocalDataSource)
    }

    @Test
    fun `nuke should return Failure`() {
        whenever(scheduleLocalDataSource.nuke())
            .doReturn(getCompletableError())

        val testObserver = scheduleRepositoryImpl.nuke().test()

        testObserver.assertGeneralsError()
    }
    // endregion

}