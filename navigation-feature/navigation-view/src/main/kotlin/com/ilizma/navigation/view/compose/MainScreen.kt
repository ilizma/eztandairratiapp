package com.ilizma.navigation.view.compose

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.compose.MenuScreen
import com.ilizma.navigation.view.model.NavigationBarItemType
import com.ilizma.navigation.view.model.NavigationBarItemType.MENU
import com.ilizma.navigation.view.model.NavigationBarItemType.RADIO
import com.ilizma.navigation.view.model.NavigationBarItemType.SCHEDULE
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.compose.RadioScreen
import com.ilizma.resources.R
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.compose.ScheduleScreen

@Composable
fun MainScreen(
    lazyViewModels: List<Lazy<ViewModel>>,
    mainNavController: NavHostController,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var navigationBarItemType by rememberSaveable { mutableStateOf(RADIO) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(navigationBarItemType) },
        bottomBar = {
            BottomBar(
                navigationBarItemType = navigationBarItemType,
                itemSelected = {
                    navigationBarItemType = it
                    mainNavController.navigate(it.name)
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = RADIO.name,
        ) {
            composable(RADIO.name) {
                navigationBarItemType = RADIO
                lazyViewModels.first { it.value is RadioScreenViewModel }
                    .value
                    .let { it as RadioScreenViewModel }
                    .let {
                        BackHandler { it.onBack() }
                        RadioScreen(
                            viewModel = it,
                            paddingValues = paddingValues,
                            snackbarHostState = snackbarHostState,
                        )
                    }
            }
            composable(SCHEDULE.name) { _ ->
                navigationBarItemType = SCHEDULE
                lazyViewModels.first { it.value is ScheduleScreenViewModel }
                    .value
                    .let { it as ScheduleScreenViewModel }
                    .let {
                        BackHandler { it.onBack() }
                        ScheduleScreen(
                            viewModel = it,
                            paddingValues = paddingValues,
                        )
                    }
            }
            composable(MENU.name) { _ ->
                navigationBarItemType = MENU
                lazyViewModels.first { it.value is MenuScreenViewModel }
                    .value
                    .let { it as MenuScreenViewModel }
                    .let {
                        BackHandler { it.onBack() }
                        MenuScreen(
                            viewModel = it,
                            paddingValues = paddingValues,
                        )
                    }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navigationBarItemType: NavigationBarItemType,
) {
    TopAppBar(
        title = {
            Text(
                text = when (navigationBarItemType) {
                    RADIO -> stringResource(R.string.title_radio)
                    SCHEDULE -> stringResource(R.string.title_schedule)
                    MENU -> stringResource(R.string.title_menu)
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
fun BottomBar(
    navigationBarItemType: NavigationBarItemType,
    itemSelected: (NavigationBarItemType) -> Unit,
) {
    NavigationBar {
        BottomBarItem(
            selected = navigationBarItemType == RADIO,
            textResource = R.string.title_radio,
            icon = Icons.Default.Radio,
            contentDescription = "Radio",
            itemSelected = { itemSelected(RADIO) },
        )
        BottomBarItem(
            selected = navigationBarItemType == SCHEDULE,
            textResource = R.string.title_schedule,
            icon = Icons.Default.Schedule,
            contentDescription = "Schedule",
            itemSelected = { itemSelected(SCHEDULE) },
        )
        BottomBarItem(
            selected = navigationBarItemType == MENU,
            textResource = R.string.title_menu,
            icon = Icons.Default.Menu,
            contentDescription = "Menu",
            itemSelected = { itemSelected(MENU) },
        )
    }
}

@Composable
fun RowScope.BottomBarItem(
    selected: Boolean,
    @StringRes textResource: Int,
    icon: ImageVector,
    contentDescription: String,
    itemSelected: () -> Unit
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
    )
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview(
    @PreviewParameter(MainScreenPreviewProvider::class) lazyViewModels: List<Lazy<ViewModel>>,
    mainNavController: NavHostController = rememberNavController(),
) {
    MainScreen(
        lazyViewModels = lazyViewModels,
        mainNavController = mainNavController,
    )
}

private class MainScreenPreviewProvider : PreviewParameterProvider<List<Lazy<ViewModel>>> {
    override val values: Sequence<List<Lazy<ViewModel>>> = sequenceOf(
        listOf(lazy { FakeViewModel() }),
    )

    class FakeViewModel : ViewModel()
}
