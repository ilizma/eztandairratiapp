package com.ilizma.app.controller

import androidx.compose.ui.window.ComposeUIViewController
import com.ilizma.app.di.initKoin
import com.ilizma.main.view.compose.AppNavigation
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin(
            isDebug = Platform.isDebugBinary,
        )
    },
) {
    AppNavigation(
        radioScreenRouter = koinInject(),
        radioScreenViewModel = koinViewModel(),
    )
}