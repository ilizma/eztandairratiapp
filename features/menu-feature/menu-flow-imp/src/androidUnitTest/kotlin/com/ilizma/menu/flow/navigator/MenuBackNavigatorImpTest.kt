package com.ilizma.menu.flow.navigator

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.ilizma.player.flow.model.RadioTab
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.BeforeTest

class MenuBackNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navController: NavHostController

    private lateinit var navigator: MenuBackNavigator

    init {
        MockKAnnotations.init(this)

        every { navController.currentBackStackEntry?.lifecycle?.currentState } returns Lifecycle.State.RESUMED
    }

    @BeforeTest
    fun setup() {
        navigator = MenuBackNavigatorImp()
    }

    @Test
    fun `when back, then popBackStack should be executed`() {
        // when
        navigator.back(navController)

        // then
        verify { navController.popBackStack(route = RadioTab, inclusive = false) }
    }

}