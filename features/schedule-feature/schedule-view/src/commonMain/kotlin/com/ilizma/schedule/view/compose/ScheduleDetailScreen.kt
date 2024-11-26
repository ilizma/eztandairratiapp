package com.ilizma.schedule.view.compose

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ilizma.resources.Res
import com.ilizma.resources.empty_list
import com.ilizma.resources.retry
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.view.shimmer.ShimmerBrush
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
fun ScheduleDetailScreen(
    viewModel: ScheduleDetailScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    //TODO BackHandler { viewModel.onBack() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = (viewModel.scheduleState
                    .collectAsStateWithLifecycle(
                        initialValue = ScheduleState.Loading(listOf()),
                        lifecycleOwner = LocalLifecycleOwner.current,
                    ).value as? ScheduleState.Success)?.title.orEmpty(),
                onBackClick = { viewModel.onBack() },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        viewModel.scheduleState
            .collectAsStateWithLifecycle(
                initialValue = ScheduleState.Loading(listOf()),
                lifecycleOwner = LocalLifecycleOwner.current,
            ).value
            .let {
                when (it) {
                    is ScheduleState.Loading,
                    is ScheduleState.Success,
                        -> when (it.list.isEmpty()) {
                        true -> EmptyView(
                            paddingValues = paddingValues,
                        )

                        false -> Schedule(
                            paddingValues = paddingValues,
                            list = it.list,
                        )
                    }

                    is ScheduleState.Error -> if (it.message.isNotEmpty()) {
                        Error(
                            snackbarHostState = snackbarHostState,
                            errorMessage = it.message,
                            onClick = { viewModel.retrySchedule() }
                        )
                    }
                }

            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
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
            key = {
                when (it) {
                    is ProgramType.Loading -> {}
                    is ProgramType.Item -> it.hour
                }
            },
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
                )
        ) {
            Text(text = program.hour)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = program.name, fontWeight = FontWeight.Bold)
            if (program.repeated) {
                Spacer(modifier = Modifier.width(4.dp))
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
    @PreviewParameter(ScheduleDetailScreenPreviewProvider::class) viewModel: ScheduleDetailScreenViewModel,
) {
    ScheduleDetailScreen(viewModel)
}

private class ScheduleDetailScreenPreviewProvider :
    PreviewParameterProvider<ScheduleDetailScreenViewModel> {
    override val values: Sequence<ScheduleDetailScreenViewModel> = sequenceOf(
        FakeScreenViewModel(
            listOf(
                ProgramType.Loading,
                ProgramType.Loading,
            )
        ),
        FakeScreenViewModel(
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
            )
        ),
        FakeScreenViewModel(),
    )

    @Suppress("UNCHECKED_CAST")
    class FakeScreenViewModel(
        list: List<ProgramType> = listOf(),
    ) : ScheduleDetailScreenViewModel() {
        override val scheduleState: Flow<ScheduleState> = flowOf(
            when (list.firstOrNull()) {
                is ProgramType.Loading -> ScheduleState.Loading(list as List<ProgramType.Loading>)
                is ProgramType.Item -> ScheduleState.Success(
                    "Monday",
                    list as List<ProgramType.Item>
                )

                else -> ScheduleState.Success("Monday", listOf())
            }
        )
        override val navigationAction: Flow<ScheduleDetailNavigationAction>
            get() = TODO("Fake VM")

        override fun saveCache(id: Int, name: String) {
        }

        override fun getSchedule() {
        }

        override fun retrySchedule() {
        }

        override fun onBack() {
        }

    }
}
