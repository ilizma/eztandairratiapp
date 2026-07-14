package com.ilizma.main.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.WatchLater
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Radio
import androidx.compose.material.icons.outlined.WatchLater
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ilizma.menu.flow.model.MenuTab
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.compose.MenuScreen
import com.ilizma.player.flow.model.RadioTab
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.compose.RadioScreen
import com.ilizma.resources.Res
import com.ilizma.resources.title_menu
import com.ilizma.resources.title_radio
import com.ilizma.resources.title_schedule
import com.ilizma.schedule.flow.model.ScheduleTab
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.component.ScheduleScreen
import com.ilizma.view.navigation.NavigationState
import com.ilizma.view.navigation.Navigator
import com.ilizma.view.navigation.toEntries
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun BottomNavigation(
    navigationState: NavigationState,
    navigator: Navigator,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                key = navigationState.topLevelRoute,
            )
        },
        bottomBar = {
            BottomBar(
                key = navigationState.topLevelRoute,
                itemSelected = { navigator.navigate(it as NavKey) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Content(
            navigationState = navigationState,
            navigator = navigator,
            snackbarHostState = snackbarHostState,
            paddingValues = paddingValues,
            radioScreenViewModel = radioScreenViewModel,
            scheduleScreenViewModel = scheduleScreenViewModel,
            menuScreenViewModel = menuScreenViewModel,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    key: NavKey,
) {
    TopAppBar(
        title = {
            Text(
                text = when (key) {
                    RadioTab -> stringResource(Res.string.title_radio)
                    ScheduleTab -> stringResource(Res.string.title_schedule)
                    MenuTab -> stringResource(Res.string.title_menu)
                    else -> ""
                }
            )
        },
    )
}

@Composable
private fun BottomBar(
    key: NavKey,
    itemSelected: (Any) -> Unit,
) {
    NavigationBar {
        BottomBarItem(
            selected = key == RadioTab,
            textResource = Res.string.title_radio,
            icon = if (key == RadioTab) Icons.Filled.Radio else Icons.Outlined.Radio,
            contentDescription = "Radio",
            itemSelected = { itemSelected(RadioTab) },
        )
        BottomBarItem(
            selected = key == ScheduleTab,
            textResource = Res.string.title_schedule,
            icon = if (key == ScheduleTab) Icons.Filled.WatchLater else Icons.Outlined.WatchLater,
            contentDescription = "Schedule",
            itemSelected = { itemSelected(ScheduleTab) },
        )
        BottomBarItem(
            selected = key == MenuTab,
            textResource = Res.string.title_menu,
            icon = if (key == MenuTab) Icons.AutoMirrored.Filled.MenuOpen else Icons.Outlined.Menu,
            contentDescription = "Menu",
            itemSelected = { itemSelected(MenuTab) },
        )
    }
}

@Composable
private fun RowScope.BottomBarItem(
    selected: Boolean,
    textResource: StringResource,
    icon: ImageVector,
    contentDescription: String,
    itemSelected: () -> Unit,
) {
    NavigationBarItem(
        selected = selected,
        onClick = { itemSelected() },
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
            )
        },
        label = {
            Text(
                text = stringResource(textResource),
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}

@Composable
private fun Content(
    navigationState: NavigationState,
    navigator: Navigator,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
) {
    val entryProvider = entryProvider {
        entry<RadioTab> {
            RadioScreen(
                viewModel = radioScreenViewModel,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
            )
        }

        entry<ScheduleTab> {
            ScheduleScreen(
                viewModel = scheduleScreenViewModel,
                paddingValues = paddingValues,
            )
        }

        entry<MenuTab> {
            MenuScreen(
                viewModel = menuScreenViewModel,
                paddingValues = paddingValues,
            )
        }
    }

    NavDisplay(
        entries = navigationState.toEntries(entryProvider),
        onBack = { navigator.goBack() }
    )
}
