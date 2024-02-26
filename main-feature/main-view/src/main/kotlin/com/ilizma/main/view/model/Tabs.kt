package com.ilizma.main.view.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.compose.MenuScreen
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.compose.RadioScreen
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.compose.ScheduleScreen

class RadioTab(
    private val viewModel: RadioScreenViewModel,
    private val snackbarHostState: SnackbarHostState,
) : Tab {

    lateinit var paddingValues: PaddingValues

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Radio)
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Radio",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        RadioScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
            snackbarHostState = snackbarHostState,
        )
    }

}

class ScheduleTab(
    private val viewModel: ScheduleScreenViewModel,
) : Tab {

    lateinit var paddingValues: PaddingValues

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Schedule)
            return remember {
                TabOptions(
                    index = 1u,
                    title = "Schedule",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        ScheduleScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
        )
    }

}

class MenuTab(
    private val viewModel: MenuScreenViewModel,
) : Tab {

    lateinit var paddingValues: PaddingValues

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(Icons.Default.Menu)
            return remember {
                TabOptions(
                    index = 2u,
                    title = "Menu",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        MenuScreen(
            viewModel = viewModel,
            paddingValues = paddingValues,
        )
    }

}