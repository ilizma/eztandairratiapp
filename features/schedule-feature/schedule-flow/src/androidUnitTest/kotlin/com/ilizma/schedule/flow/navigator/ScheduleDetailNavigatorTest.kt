package com.ilizma.schedule.flow.navigator

import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.view.navigation.Navigator
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class ScheduleDetailNavigatorTest {

    @RelaxedMockK
    private lateinit var navigatorMock: Navigator

    private lateinit var navigator: ScheduleDetailNavigator

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        navigator = ScheduleDetailNavigator()
    }

    @Test
    fun `when navigate, then navigate should be executed`() {
        // given
        val id = 1
        val name = "name"
        val expected = ScheduleDetail(id = id, name = name)

        // when
        navigator.navigate(navigatorMock, id, name)

        // then
        verify { navigatorMock.navigate(expected) }
    }

}
