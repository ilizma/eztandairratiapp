package com.ilizma.data.repository.schedule.datasources

import com.ilizma.data.datasources.local.SharedPreferencesAssistant
import com.ilizma.data.datasources.local.SharedPreferencesKeys.SCHEDULE_KEY
import com.ilizma.data.entity.schedule.ScheduleFactory.Companion.providesData
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.data.extensions.getSingleSuccess
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Lazy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ScheduleLocalDataSourceUnitTest {

    private lateinit var scheduleLocalDataSource: ScheduleLocalDataSource

    @Mock
    private lateinit var assistant: SharedPreferencesAssistant

    @Mock
    private lateinit var lazyMoshi: Lazy<Moshi>

    @Before
    fun setUp() {
        scheduleLocalDataSource = ScheduleLocalDataSource(assistant)
        val moshi = Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())
            .build()

        scheduleLocalDataSource.moshi = lazyMoshi
        whenever(lazyMoshi.get())
            .doReturn(moshi)
    }

    @Test
    fun `saveSchedule should call assistant saveString`() {
        val data = providesData()

        scheduleLocalDataSource.saveSchedule(data.schedule)

        scheduleLocalDataSource.run {
            verify(assistant).saveString(SCHEDULE_KEY, data.schedule.toJson())
        }
    }

    @Test
    fun `getSchedule should return schedule single`() {
        val data = providesData()
        scheduleLocalDataSource.run {
            val json = data.schedule.toJson()
            whenever(assistant.getString(SCHEDULE_KEY))
                .doReturn(getSingleSuccess(json))
        }

        val testObserver = scheduleLocalDataSource.getSchedule().test()

        testObserver.assertGeneralsSuccess {
            it.isNotEmpty()
        }
        verify(assistant).getString(SCHEDULE_KEY)
    }

    @Test
    fun `nuke should call assistant nuke`() {
        scheduleLocalDataSource.nuke()

        verify(assistant).nuke()
    }

}