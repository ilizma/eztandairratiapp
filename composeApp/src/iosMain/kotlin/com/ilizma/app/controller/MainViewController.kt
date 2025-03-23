package com.ilizma.app.controller

import androidx.compose.ui.window.ComposeUIViewController
import com.ilizma.app.di.initKoin
import com.ilizma.main.view.compose.AppNavigation
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.crashlytics.crashlytics
import dev.gitlive.firebase.initialize
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

fun initialise() {
    Firebase.initialize()
    Firebase.crashlytics.setCrashlyticsCollectionEnabled(true)
}