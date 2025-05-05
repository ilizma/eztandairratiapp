package com.ilizma.menu.presentation.viewmodel

import com.ilizma.menu.presentation.model.MenuNavigationAction.Back
import com.ilizma.menu.presentation.model.MenuNavigationAction.Facebook
import com.ilizma.menu.presentation.model.MenuNavigationAction.Instagram
import com.ilizma.menu.presentation.model.MenuNavigationAction.Phone
import com.ilizma.menu.presentation.model.MenuNavigationAction.Twitter
import com.ilizma.menu.presentation.model.MenuNavigationAction.Web
import com.ilizma.menu.presentation.model.MenuNavigationAction.WhatsApp
import com.ilizma.menu.presentation.model.MenuScreenIntent
import io.mockk.MockKAnnotations
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MenuScreenViewModelImpTest {

    private lateinit var viewModel: MenuScreenViewModel

    init {
        MockKAnnotations.init(this)
    }

    private fun setup(testDispatcher: TestDispatcher) {
        viewModel = MenuScreenViewModelImp(
            dispatcher = testDispatcher,
            _navigationAction = MutableSharedFlow(),
        )
    }

    @Test
    fun `given Instagram MenuIntent, when onIntent, then result should be the expected Instagram`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = Instagram

            // when
            viewModel.onIntent(MenuScreenIntent.Instagram)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

    @Test
    fun `given Twitter MenuIntent, when onIntent, then result should be the expected Twitter`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = Twitter

            // when
            viewModel.onIntent(MenuScreenIntent.Twitter)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

    @Test
    fun `given Facebook MenuIntent, when onIntent, then result should be the expected Facebook`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = Facebook

            // when
            viewModel.onIntent(MenuScreenIntent.Facebook)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

    @Test
    fun `given Phone MenuIntent, when onIntent, then result should be the expected Phone`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = Phone

            // when
            viewModel.onIntent(MenuScreenIntent.Phone)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

    @Test
    fun `given WhatsApp MenuIntent, when onIntent, then result should be the expected WhatsApp`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = WhatsApp

            // when
            viewModel.onIntent(MenuScreenIntent.WhatsApp)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

    @Test
    fun `given Web MenuIntent, when onIntent, then result should be the expected Web`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = Web

            // when
            viewModel.onIntent(MenuScreenIntent.Web)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

    @Test
    fun `given Back MenuIntent, when onIntent, then result should be the expected Back`() =
        runTest {
            // given
            val testDispatcher = StandardTestDispatcher(testScheduler)
            setup(testDispatcher)
            val expected = Back

            // when
            viewModel.onIntent(MenuScreenIntent.Back)

            // then
            assertEquals(expected, viewModel.navigationAction.first())
        }

}