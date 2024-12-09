package com.ilizma.schedule.view.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.view.lifecycle.collectAsStateMultiplatform

@Composable
actual fun ScheduleDetailScreen(
    viewModel: ScheduleDetailScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    BackHandler { viewModel.onIntent(ScheduleDetailScreenIntent.Back) }

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

@PreviewLightDark
@Composable
private fun ScheduleDetailScreenPreview(
    @PreviewParameter(ScheduleDetailScreenPreviewProvider::class) state: ScheduleState,
) {
    EztandaIrratiappTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            Content(
                state = state,
                paddingValues = PaddingValues(),
                snackbarHostState = remember { SnackbarHostState() },
                onIntent = {},
            )
        }
    }
}

private class ScheduleDetailScreenPreviewProvider :
    PreviewParameterProvider<ScheduleState> {
    override val values: Sequence<ScheduleState> = sequenceOf(
        listOf(
            ProgramType.Loading,
            ProgramType.Loading,
        ).let { ScheduleState.Loading(it) },
        listOf(
            ProgramType.Item(
                hour = "12:00",
                day = 1,
                name = "Program 1",
                repeated = false,
            ),
            ProgramType.Item(
                hour = "13:00",
                day = 1,
                name = "Program 2",
                repeated = true,
            ),
        ).let {
            ScheduleState.Success(
                title = "Monday",
                list = it,
            )
        },
        ScheduleState.Success("Monday", listOf()),
    )
}