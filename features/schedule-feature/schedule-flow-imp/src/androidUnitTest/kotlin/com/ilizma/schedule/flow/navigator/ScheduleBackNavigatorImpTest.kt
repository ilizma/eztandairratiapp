package com.ilizma.schedule.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.ilizma.player.flow.model.RadioTab
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class ScheduleBackNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavHostController

    private lateinit var navigator: ScheduleBackNavigator

    init {
        MockKAnnotations.init(this)

        every { navController.currentBackStackEntry?.lifecycle?.currentState } returns Lifecycle.State.RESUMED
    }

    @BeforeTest
    fun setup() {
        navigator = ScheduleBackNavigatorImp()
    }

    @Test
    fun `when back, then popBackStack is called`() {
        // when
        navigator.back(navController)

        // then
        verify { navController.popBackStack(route = RadioTab, inclusive = false) }
    }
}