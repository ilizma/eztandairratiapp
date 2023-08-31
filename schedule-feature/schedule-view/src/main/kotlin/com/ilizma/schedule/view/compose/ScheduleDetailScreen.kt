package com.ilizma.schedule.view.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.ilizma.resources.R
import com.ilizma.schedule.presentation.model.ProgramType
import com.ilizma.schedule.presentation.model.ScheduleDetailNavigationAction
import com.ilizma.schedule.presentation.model.ScheduleState
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.view.shimmer.ShimmerBrush
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

@Preview
@Composable
fun ScheduleDetailScreen(
    @PreviewParameter(ScheduleDetailScreenPreviewProvider::class) viewModel: ScheduleScreenDetailViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                title = viewModel.dayName
                    .collectAsState("")
                    .value,
                onBackClick = { viewModel.onBack() },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        viewModel.scheduleState
            .collectAsState(ScheduleState.Loading(listOf()))
            .value
            .let {
                when (it) {
                    is ScheduleState.Loading,
                    is ScheduleState.Success -> when (it.list.isEmpty()) {
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
                            onClick = { viewModel.getSchedule() }
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
                    imageVector = Icons.Default.ArrowBack,
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
        Icon(painter = painterResource(id = R.drawable.ic_empty), contentDescription = "Empty list")
        Text(
            text = stringResource(R.string.empty_list),
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
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                )
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(25.dp),
            ) {
                ShimmerBrush()
            }
            Spacer(modifier = Modifier.width(4.dp))
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(25.dp)
            ) {
                ShimmerBrush()
            }
            if (Random.nextBoolean()) {
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                ) {
                    ShimmerBrush()
                }
            }
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
                    painter = painterResource(R.drawable.ic_repeated),
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
    stringResource(R.string.retry)
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

private class ScheduleDetailScreenPreviewProvider :
    PreviewParameterProvider<ScheduleScreenDetailViewModel> {
    override val values: Sequence<ScheduleScreenDetailViewModel> = sequenceOf(
        FakeViewModel(
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
                )
            )
        ),
        FakeViewModel(),
    )

    class FakeViewModel(
        list: List<ProgramType.Item> = listOf()
    ) : ScheduleScreenDetailViewModel() {
        override val dayName: Flow<String> = flowOf("Monday")

        override val scheduleState: Flow<ScheduleState> = flowOf(
            ScheduleState.Success(list)
        )
        override val navigationAction: LiveData<ScheduleDetailNavigationAction>
            get() = TODO("Not yet implemented")

        override fun getTitle() {
        }

        override fun getSchedule() {
        }

        override fun onBack() {
        }


    }
}
