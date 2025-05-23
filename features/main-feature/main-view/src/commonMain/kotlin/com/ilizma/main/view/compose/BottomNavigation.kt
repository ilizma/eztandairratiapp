package com.ilizma.main.view.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Radio
import androidx.compose.material.icons.outlined.Schedule
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ilizma.main.view.model.BottomBarItemType
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
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun BottomNavigation(
    navController: NavHostController,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navigationBarItemType = rememberSaveable { mutableStateOf(BottomBarItemType.RADIO) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar(
                key = navigationBarItemType.value,
            )
        },
        bottomBar = {
            BottomBar(
                key = navigationBarItemType.value,
                itemSelected = { navController.navigate(it) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Content(
            navigationBarItemType = { navigationBarItemType.value = it },
            navController = navController,
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
    key: BottomBarItemType,
) {
    TopAppBar(
        title = {
            Text(
                text = when (key) {
                    BottomBarItemType.RADIO -> stringResource(Res.string.title_radio)
                    BottomBarItemType.SCHEDULE -> stringResource(Res.string.title_schedule)
                    BottomBarItemType.MENU -> stringResource(Res.string.title_menu)
                }
            )
        },
//        actions = when (navigationBarItemType) {
//            RADIO -> {
//                val activity = LocalContext.current as? AppCompatActivity ?: return
//                {
//                    Icon(
//                        painter = painterResource(id = androidx.mediarouter.R.drawable.ic_mr_button_connected_24_dark),
//                        contentDescription = "Cast",
//                        modifier = Modifier
//                            .clickable {
//                                MediaRouteDialogFactory.getDefault()
//                                    .onCreateChooserDialogFragment()
//                                    .apply {
//                                        routeSelector = MediaRouteSelector.Builder()
//                                            .addControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO)
//                                            .addControlCategory(MediaControlIntent.CATEGORY_REMOTE_PLAYBACK)
//                                            .build()
//                                    }.let { fragment ->
//                                        activity.supportFragmentManager.beginTransaction()
//                                            ?.apply {
//                                                add(fragment, "MediaRouteChooserDialogFragment")
//                                            }
//                                            ?.commitAllowingStateLoss()
//                                    }
//                            },
//                    )
//                }
//            }
//
//            else -> {
//                {}
//            }
//        },
    )
}

@Composable
private fun BottomBar(
    key: BottomBarItemType,
    itemSelected: (Any) -> Unit,
) {
    NavigationBar {
        BottomBarItem(
            selected = key == BottomBarItemType.RADIO,
            textResource = Res.string.title_radio,
            icon = if (key == BottomBarItemType.RADIO) Icons.Filled.Radio else Icons.Outlined.Radio,
            contentDescription = "Radio",
            itemSelected = { itemSelected(RadioTab) },
        )
        BottomBarItem(
            selected = key == BottomBarItemType.SCHEDULE,
            textResource = Res.string.title_schedule,
            icon = if (key == BottomBarItemType.SCHEDULE) Icons.Filled.Schedule else Icons.Outlined.Schedule,
            contentDescription = "Schedule",
            itemSelected = { itemSelected(ScheduleTab) },
        )
        BottomBarItem(
            selected = key == BottomBarItemType.MENU,
            textResource = Res.string.title_menu,
            icon = if (key == BottomBarItemType.MENU) Icons.Filled.Menu else Icons.Outlined.Menu,
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
    navigationBarItemType: (BottomBarItemType) -> Unit,
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    paddingValues: PaddingValues,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
) {
    NavHost(navController = navController, startDestination = RadioTab) {
        composable<RadioTab> {
            navigationBarItemType(BottomBarItemType.RADIO)
            RadioScreen(
                viewModel = radioScreenViewModel,
                paddingValues = paddingValues,
                snackbarHostState = snackbarHostState,
            )
        }

        composable<ScheduleTab> {
            navigationBarItemType(BottomBarItemType.SCHEDULE)
            ScheduleScreen(
                viewModel = scheduleScreenViewModel,
                paddingValues = paddingValues,
            )
        }

        composable<MenuTab> {
            navigationBarItemType(BottomBarItemType.MENU)
            MenuScreen(
                viewModel = menuScreenViewModel,
                paddingValues = paddingValues,
            )
        }
    }
}