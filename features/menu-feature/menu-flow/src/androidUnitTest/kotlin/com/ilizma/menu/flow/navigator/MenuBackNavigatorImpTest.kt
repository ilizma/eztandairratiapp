package com.ilizma.menu.flow.navigator

import com.ilizma.view.navigation.Navigator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class MenuBackNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navigatorMock: Navigator

    private lateinit var navigator: MenuBackNavigator

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        navigator = MenuBackNavigatorImp()
    }

    @Test
    fun `when back, then goBack should be executed`() {
        // when
        navigator.back(navigatorMock)

        // then
        verify { navigatorMock.goBack() }
    }

}
