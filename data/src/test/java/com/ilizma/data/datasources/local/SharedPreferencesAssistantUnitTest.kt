package com.ilizma.data.datasources.local

import android.content.Context
import android.content.SharedPreferences
import com.ilizma.data.extensions.assertGeneralsCompletableSuccess
import com.ilizma.data.extensions.assertGeneralsError
import com.ilizma.data.extensions.assertGeneralsSuccess
import com.ilizma.domain.entity.base.Failure
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesAssistantUnitTest {

    private lateinit var sharedPreferencesAssistant: SharedPreferencesAssistant

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    @Mock
    private lateinit var context: Context

    private val key = "key"
    private val stringValue = "stringValue"
    private val booleanValue = true
    private val intValue = 123456

    @Before
    fun setUp() {
        whenever(context.getSharedPreferences(anyString(), anyInt()))
            .doReturn(sharedPreferences)
        whenever(context.getSharedPreferences(anyString(), anyInt()).edit())
            .doReturn(sharedPreferencesEditor)
        sharedPreferencesAssistant = SharedPreferencesAssistant(sharedPreferences)
    }

    @Test
    fun `saveString should save String`() {
        whenever(sharedPreferencesEditor.putString(anyString(), anyString()))
            .doReturn(sharedPreferencesEditor)

        sharedPreferencesAssistant.saveString(key, stringValue)

        verify(sharedPreferencesEditor).putString(key, stringValue)
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `getString should return String`() {
        whenever(sharedPreferences.getString(anyString(), anyString()))
            .doReturn(stringValue)

        val testObserver = sharedPreferencesAssistant.getString(key).test()

        testObserver.assertGeneralsSuccess {
            it == stringValue
        }
    }

    @Test
    fun `getString should return NotInDatabase`() {
        whenever(sharedPreferences.getString(anyString(), anyString()))
            .doReturn(STRING_DEFAULT_VALUE)

        val testObserver = sharedPreferencesAssistant.getString(key).test()

        testObserver.assertGeneralsError {
            it is Failure.NotInDatabase
        }
    }

    @Test
    fun `saveBoolean should save Boolean`() {
        whenever(sharedPreferencesEditor.putBoolean(anyString(), anyBoolean()))
            .doReturn(sharedPreferencesEditor)

        sharedPreferencesAssistant.saveBoolean(key, booleanValue)

        verify(sharedPreferencesEditor).putBoolean(key, booleanValue)
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `getBoolean should return Boolean`() {
        whenever(sharedPreferences.getBoolean(anyString(), anyBoolean()))
            .doReturn(booleanValue)

        val testObserver = sharedPreferencesAssistant.getBoolean(key).test()

        testObserver.assertGeneralsSuccess {
            it == booleanValue
        }
    }

    @Test
    fun `saveInt should save Int`() {
        whenever(sharedPreferencesEditor.putInt(anyString(), anyInt()))
            .doReturn(sharedPreferencesEditor)

        sharedPreferencesAssistant.saveInt(key, intValue)

        verify(sharedPreferencesEditor).putInt(key, intValue)
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `getInt should return Int`() {
        whenever(sharedPreferences.getInt(anyString(), anyInt()))
            .doReturn(intValue)

        val testObserver = sharedPreferencesAssistant.getInt(key).test()

        testObserver.assertGeneralsSuccess {
            it == intValue
        }
    }

    @Test
    fun `getInt should return NotInDatabase`() {
        whenever(sharedPreferences.getInt(anyString(), anyInt()))
            .doReturn(INT_DEFAULT_VALUE)

        val testObserver = sharedPreferencesAssistant.getInt(key).test()

        testObserver.assertGeneralsError {
            it is Failure.NotInDatabase
        }
    }

    @Test
    fun `removeKey should remove key`() {
        whenever(sharedPreferencesEditor.remove(anyString()))
            .doReturn(sharedPreferencesEditor)

        sharedPreferencesAssistant.removeKey(key)

        verify(sharedPreferencesEditor).remove(key)
        verify(sharedPreferencesEditor).apply()
    }

    @Test
    fun `nuke should clear database`() {
        whenever(sharedPreferencesEditor.clear())
            .doReturn(sharedPreferencesEditor)

        val testObserver = sharedPreferencesAssistant.nuke().test()

        testObserver.assertGeneralsCompletableSuccess()
        verify(sharedPreferencesEditor).clear()
        verify(sharedPreferencesEditor).apply()
    }

}