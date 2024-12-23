package com.ilizma.menu.flow.navigator

import androidx.compose.ui.platform.UriHandler
import com.ilizma.menu.flow.model.WEB_URL
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class WebNavigatorImpTest {

    @RelaxedMockK
    private lateinit var uriHandler: UriHandler

    private lateinit var navigator: WebNavigator

    init {
        MockKAnnotations.init(this)
    }

    @BeforeTest
    fun setup() {
        navigator = WebNavigatorImp()
    }

    @Test
    fun `given uriHandler, when navigate, then openUri`() {
        // when
        navigator.navigate(uriHandler)

        // then
        verify { uriHandler.openUri(WEB_URL) }
    }

}