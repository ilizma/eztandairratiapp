package com.ilizma.player.flow.navigator

import android.app.Activity
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class RadioCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var activity: Activity

    private lateinit var navigator: RadioCloseNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = RadioCloseNavigatorImp(
            activity = activity,
        )
    }

    @Test
    fun `when close is invoked, then activity finish is called`() {
        // when
        navigator.close()

        // then
        verify { activity.finish() }
    }
}