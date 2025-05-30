package com.ilizma.menu.flow.navigator

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.BeforeTest

class WhatsAppNavigatorImpTest {

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var navigator: WhatsAppNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = WhatsAppNavigatorImp(
            context = context,
        )
    }

    @Test
    fun `when navigate, then startActivity`() {
        // when
        navigator.navigate()

        // then
        verify { context.startActivity(any()) }
    }

}