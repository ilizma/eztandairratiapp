package com.ilizma.schedule.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Test
import kotlin.test.BeforeTest

class ScheduleDetailCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavHostController

    private lateinit var navigator: ScheduleDetailCloseNavigator

    init {
        MockKAnnotations.init(this)

        every { navController.currentBackStackEntry?.lifecycle?.currentState } returns Lifecycle.State.RESUMED
    }

    @BeforeTest
    fun setup() {
        navigator = ScheduleDetailCloseNavigatorImp()
    }

    @Test
    fun `when back, then popBackStack is called`() {
        // when
        navigator.close(navController)

        // then
        verify { navController.popBackStack() }
    }
}