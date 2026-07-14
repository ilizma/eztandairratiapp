package com.ilizma.schedule.flow.navigator

import com.ilizma.view.navigation.Navigator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class ScheduleDetailCloseNavigatorImpTest {

    @RelaxedMockK
    private lateinit var navigatorMock: Navigator

    private lateinit var navigator: ScheduleDetailCloseNavigator

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        navigator = ScheduleDetailCloseNavigatorImp()
    }

    @Test
    fun `when close, then goBack should be executed`() {
        // when
        navigator.close(navigatorMock)

        // then
        verify { navigatorMock.goBack() }
    }

}
