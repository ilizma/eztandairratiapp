package com.ilizma.data.repository.schedule.datasources

import com.ilizma.data.entity.schedule.ScheduleResponseFactory.Companion.providesDataResponse
import com.ilizma.data.extensions.assertGeneralsError
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.data.extensions.getSingleResultError
import com.ilizma.data.extensions.getSingleResultSuccess
import com.ilizma.data.repository.schedule.data.ScheduleApi
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ScheduleRemoteDataSourceUnitTest {

    private lateinit var scheduleRemoteDataSource: ScheduleRemoteDataSource

    @Mock
    private lateinit var scheduleApi: ScheduleApi

    @Before
    fun setUp() {
        scheduleRemoteDataSource = ScheduleRemoteDataSource(scheduleApi)
    }

    // region Success cases
    @Test
    fun `getSchedule should return data`() {
        val data = providesDataResponse()
        whenever(scheduleApi.getSchedule())
            .doReturn(getSingleResultSuccess(data))

        val testObserver = scheduleRemoteDataSource.getSchedule().test()

        testObserver.assertGeneralsSuccess()
    }
    // endregion

    // region Failure cases
    @Test
    fun `getSchedule should return Failure`() {
        whenever(scheduleApi.getSchedule())
            .doReturn(getSingleResultError())

        val testObserver = scheduleRemoteDataSource.getSchedule().test()

        testObserver.assertGeneralsError()
    }
    // endregion

}