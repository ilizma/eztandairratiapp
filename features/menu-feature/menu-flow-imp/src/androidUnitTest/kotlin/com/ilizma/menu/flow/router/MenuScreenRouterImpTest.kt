package com.ilizma.menu.flow.router

import com.ilizma.menu.flow.navigator.FacebookNavigator
import com.ilizma.menu.flow.navigator.InstagramNavigator
import com.ilizma.menu.flow.navigator.MenuBackNavigator
import com.ilizma.menu.flow.navigator.PhoneNavigator
import com.ilizma.menu.flow.navigator.TwitterNavigator
import com.ilizma.menu.flow.navigator.WebNavigator
import com.ilizma.menu.flow.navigator.WhatsAppNavigator
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class MenuScreenRouterImpTest {

    @RelaxedMockK
    private lateinit var viewModel: MenuScreenViewModel

    @RelaxedMockK
    private lateinit var instagramNavigator: InstagramNavigator

    @RelaxedMockK
    private lateinit var twitterNavigator: TwitterNavigator

    @RelaxedMockK
    private lateinit var facebookNavigator: FacebookNavigator

    @RelaxedMockK
    private lateinit var phoneNavigator: PhoneNavigator

    @RelaxedMockK
    private lateinit var whatsAppNavigator: WhatsAppNavigator

    @RelaxedMockK
    private lateinit var webNavigator: WebNavigator

    @RelaxedMockK
    private lateinit var menuBackNavigator: MenuBackNavigator

    private lateinit var router: MenuScreenRouter


    init {
        MockKAnnotations.init(this)
    }

    private fun setup(testDispatcher: TestDispatcher) {
        router = MenuScreenRouterImp(
            dispatcher = testDispatcher,
            instagramNavigator = instagramNavigator,
            twitterNavigator = twitterNavigator,
            facebookNavigator = facebookNavigator,
            phoneNavigator = phoneNavigator,
            whatsAppNavigator = whatsAppNavigator,
            webNavigator = webNavigator,
            menuBackNavigator = menuBackNavigator,
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when router is initialized, then viewModel functions are called`() = runTest {
        // given
        val testDispatcher = StandardTestDispatcher(testScheduler)
        setup(testDispatcher)

        // when
        router.init(
            coroutineScope = CoroutineScope(testDispatcher),
            viewModel = viewModel,
            navController = mockk(relaxed = true),
            uriHandler = mockk(relaxed = true),
        )
        advanceUntilIdle()

        // then
        coVerify { viewModel.navigationAction.collect(any()) }
    }

}