package com.ilizma.schedule.view.component

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ilizma.view.navigation.LocalNavAnimatedVisibilityScope
import com.ilizma.view.navigation.LocalSharedTransitionScope
import com.ilizma.resources.Res
import com.ilizma.resources.empty_list
import com.ilizma.resources.retry
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.view.utils.ScheduleDetailScreenPreviewProvider
import com.ilizma.view.lifecycle.collectAsStateMultiplatform
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ScheduleDetailScreen(
    viewModel: ScheduleDetailScreenViewModel,
) {
    BackHandler { viewModel.onIntent(ScheduleDetailScreenIntent.Back) }
    ScheduleDetailScreenContent(
        viewModel = viewModel,
    )
}

@Composable
internal fun ScheduleDetailScreenContent(
    viewModel: ScheduleDetailScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.scheduleState
        .collectAsStateMultiplatform(
            initialValue = ScheduleState.Loading(list = listOf()),
        ).value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = (state as? ScheduleState.Success)?.title.orEmpty(),
                dayId = state.dayId,
                onBackClick = { viewModel.onIntent(ScheduleDetailScreenIntent.Back) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        ScreenState(
            state = state,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
            onIntent = { viewModel.onIntent(it) },
        )
    }
}

@Composable
internal fun ScreenState(
    state: ScheduleState,
    paddingValues: PaddingValues,
    snackbarHostState: SnackbarHostState,
    onIntent: (ScheduleDetailScreenIntent) -> Unit,
) {
    when (state) {
        is ScheduleState.Loading,
        is ScheduleState.Success,
            -> when (state.list.isEmpty()) {
            true -> EmptyView(
                paddingValues = paddingValues,
            )

            false -> Schedule(
                paddingValues = paddingValues,
                dayId = state.dayId,
                list = state.list,
            )
        }

        is ScheduleState.Error -> if (state.message.isNotEmpty()) {
            Error(
                snackbarHostState = snackbarHostState,
                errorMessage = state.message,
                onClick = { onIntent(ScheduleDetailScreenIntent.RetrySchedule) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
internal fun TopBar(
    title: String,
    dayId: Int,
    onBackClick: () -> Unit,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current

    TopAppBar(
        title = {
            Text(
                modifier = Modifier.then(
                    if (sharedTransitionScope != null && animatedVisibilityScope != null && dayId != -1) {
                        with(sharedTransitionScope) {
                            Modifier.sharedElement(
                                rememberSharedContentState(key = "day-name-$dayId"),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                        }
                    } else Modifier
                ),
                text = title
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
private fun EmptyView(
    paddingValues: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Default.PlaylistRemove,
            contentDescription = "Empty list",
        )
        Text(
            text = stringResource(Res.string.empty_list),
            modifier = Modifier
                .padding(top = 16.dp),
        )
    }
}

@Composable
private fun Schedule(
    paddingValues: PaddingValues,
    dayId: Int,
    list: List<ProgramType>,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (sharedTransitionScope != null && animatedVisibilityScope != null && dayId != -1) {
                    with(sharedTransitionScope) {
                        Modifier.sharedElement(
                            rememberSharedContentState(key = "day-$dayId"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    }
                } else Modifier
            ),
        contentPadding = paddingValues,
    ) {
        items(
            items = list,
        ) { program ->
            when (program) {
                is ProgramType.Loading -> LoadingRow()
                is ProgramType.Item -> ProgramRow(program = program)
            }
        }
    }
}

@Composable
private fun LoadingRow() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
    ) {
        LinearWavyProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Composable
private fun ProgramRow(
    program: ProgramType.Item,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp,
            ),
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = program.hour)
            Text(text = program.name, fontWeight = FontWeight.Bold)
            if (program.repeated) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Phone",
                )
            }

        }
    }
}

@Composable
private fun Error(
    snackbarHostState: SnackbarHostState,
    errorMessage: String,
    onClick: () -> Unit,
) {
    stringResource(Res.string.retry)
        .let { retryString ->
            LaunchedEffect(snackbarHostState) {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = retryString,
                ).let { snackbarResult ->
                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                        onClick()
                    }
                }
            }
        }
}

@Preview
@Composable
private fun ScheduleDetailScreenPreview(
    @PreviewParameter(ScheduleDetailScreenPreviewProvider::class) state: ScheduleState,
) {
    EztandaIrratiappTheme(dynamicColor = false) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { paddingValues ->
            ScreenState(
                state = state,
                paddingValues = paddingValues,
                snackbarHostState = SnackbarHostState(),
                onIntent = {},
            )
        }
    }
}
