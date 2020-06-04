package com.ilizma.data.repository.schedule

import com.ilizma.data.entity.schedule.ScheduleFactory.Companion.providesSchedule
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

private const val DAY = 1

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
    fun `getSchedule should return complete save scheduleList`() {
        val scheduleList = listOf(providesSchedule(), providesSchedule())
        whenever(scheduleRemoteDataSource.getSchedule())
            .doReturn(getSingleSuccess(scheduleList))

        val testObserver = scheduleRepositoryImpl.getSchedule().test()

        testObserver.assertGeneralsCompletableSuccess()
        verify(scheduleLocalDataSource).saveSchedule(scheduleList)
    }

    @Test
    fun `getScheduleFromLocal should return scheduleList single`() {
        val scheduleList = listOf(providesSchedule(day = "1"), providesSchedule(day = "2"))
        whenever(scheduleLocalDataSource.getSchedule())
            .doReturn(getSingleSuccess(scheduleList))

        val testObserver = scheduleRepositoryImpl.getScheduleFromLocal(DAY).test()

        testObserver.assertGeneralsSuccess {
            val filteredScheduleList = scheduleList.filter { it.day == DAY.toString() }
            it.isNotEmpty() && it.size == filteredScheduleList.size && it[0].day == filteredScheduleList[0].day
        }
    }

    @Test
    fun `isScheduleFromLocal should return complete`() {
        val scheduleList = listOf(providesSchedule(), providesSchedule())
        whenever(scheduleLocalDataSource.getSchedule())
            .doReturn(getSingleSuccess(scheduleList))

        val testObserver = scheduleRepositoryImpl.isScheduleFromLocal().test()

        testObserver.assertGeneralsCompletableSuccess()
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
    fun `getScheduleFromLocal should return Failure`() {
        whenever(scheduleLocalDataSource.getSchedule())
            .doReturn(getSingleError())

        val testObserver = scheduleRepositoryImpl.getScheduleFromLocal(DAY).test()

        testObserver.assertGeneralsError()
    }

    @Test
    fun `isScheduleFromLocal should return Failure`() {
        whenever(scheduleLocalDataSource.getSchedule())
            .doReturn(getSingleError())

        val testObserver = scheduleRepositoryImpl.isScheduleFromLocal().test()

        testObserver.assertGeneralsError()
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