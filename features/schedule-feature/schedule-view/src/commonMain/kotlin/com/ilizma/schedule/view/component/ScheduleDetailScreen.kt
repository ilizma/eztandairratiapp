package com.ilizma.schedule.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlaylistRemove
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.ilizma.view.shimmer.ShimmerBrush
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

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
                ScreenState(
                    state = it,
                    paddingValues = paddingValues,
                    snackbarHostState = snackbarHostState,
                    onIntent = { viewModel.onIntent(it) },
                )
            }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    title: String,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
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
    list: List<ProgramType>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    brush = ShimmerBrush(),
                    shape = RoundedCornerShape(12.dp),
                    alpha = 0.5f,
                )
        ) {
        }
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
