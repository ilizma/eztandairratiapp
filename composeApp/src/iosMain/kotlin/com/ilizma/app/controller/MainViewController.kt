package com.ilizma.app.controller

import androidx.compose.ui.window.ComposeUIViewController
import com.ilizma.app.di.initKoin
import com.ilizma.main.view.compose.AppNavigation
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() },
) {
    AppNavigation(
        radioScreenRouter = koinInject(),
        radioScreenViewModel = koinViewModel(),
    )
}