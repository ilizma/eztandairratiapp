package com.ilizma.schedule.flow.navigator

import androidx.navigation.NavHostController
import com.ilizma.schedule.flow.model.ScheduleDetail
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class ScheduleDetailNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavHostController

    private lateinit var navigator: ScheduleDetailNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = ScheduleDetailNavigatorImp()
    }

    @Test
    fun `when navigate, then navigate is called`() {
        // when
        navigator.navigate(navController, 1, "name")

        // then
        verify { navController.navigate(route = ScheduleDetail(1, "name")) }
    }
}