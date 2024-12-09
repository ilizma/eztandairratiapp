package com.ilizma.schedule.view.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun ScheduleDetailScreen(
    viewModel: ScheduleDetailScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = (viewModel.scheduleState
                    .collectAsStateMultiplatform(
                        initialValue = ScheduleState.Loading(listOf()),
                    ).value as? ScheduleState.Success)?.title.orEmpty(),
                onBackClick = { viewModel.onIntent(ScheduleDetailScreenIntent.Back) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        viewModel.scheduleState
            .collectAsStateMultiplatform(
                initialValue = ScheduleState.Loading(listOf()),
            ).value
            .let {
                Content(
                    state = it,
                    paddingValues = paddingValues,
                    snackbarHostState = snackbarHostState,
                    onIntent = { viewModel.onIntent(it) },
                )
            }
    }
}