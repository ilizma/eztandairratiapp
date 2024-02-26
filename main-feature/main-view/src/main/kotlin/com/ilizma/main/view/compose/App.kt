package com.ilizma.main.view.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.ilizma.main.view.model.MenuTab
import com.ilizma.main.view.model.RadioTab
import com.ilizma.main.view.model.ScheduleTab
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.resources.R
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.router.ScheduleScreenRouter


class App(
    private val lazyViewModels: List<Lazy<ViewModel>>,
    private val radioScreenRouter: RadioScreenRouter,
    private val scheduleScreenRouter: ScheduleScreenRouter,
    private val scheduleDetailScreenRouter: ScheduleDetailRouter,
    private val menuScreenRouter: MenuScreenRouter,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val snackbarHostState = remember { SnackbarHostState() }

        val radioTab = RadioTab(
            viewModel = lazyViewModels.first { it.value is RadioScreenViewModel }.value as RadioScreenViewModel,
            snackbarHostState = snackbarHostState
        )
        val scheduleTab = ScheduleTab(
            viewModel = lazyViewModels.first { it.value is ScheduleScreenViewModel }.value as ScheduleScreenViewModel
        )
        val menuTab = MenuTab(
            viewModel = lazyViewModels.first { it.value is MenuScreenViewModel }.value as MenuScreenViewModel
        )

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            TabNavigator(
                tab = radioTab,
                tabDisposable = {
                    TabDisposable(
                        it,
                        listOf(radioTab, scheduleTab, menuTab)
                    )
                }
            ) { tabNavigator ->

                radioScreenRouter.init()
                scheduleScreenRouter.init(
                    navigator = navigator,
                    tabNavigator = tabNavigator,
                    tab = radioTab
                )
                scheduleDetailScreenRouter.init(navigator)
                menuScreenRouter.init(
                    navigator = tabNavigator,
                    tab = radioTab,
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(
                            key = tabNavigator.current.key,
                            radioTab = radioTab,
                            scheduleTab = scheduleTab,
                            menuTab = menuTab,
                        )
                    },
                    bottomBar = {
                        BottomBar(
                            key = tabNavigator.current.key,
                            radioTab = radioTab,
                            scheduleTab = scheduleTab,
                            menuTab = menuTab,
                            itemSelected = { tabNavigator.current = it },
                        )
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                ) { paddingValues ->
                    Content(
                        tabNavigator = tabNavigator,
                        radioTab = radioTab,
                        paddingValues = paddingValues,
                        scheduleTab = scheduleTab,
                        menuTab = menuTab
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopBar(
        key: ScreenKey,
        radioTab: RadioTab,
        scheduleTab: ScheduleTab,
        menuTab: MenuTab,
    ) {
        TopAppBar(
            title = {
                Text(
                    text = when (key) {
                        radioTab.key -> stringResource(R.string.title_radio)
                        scheduleTab.key -> stringResource(R.string.title_schedule)
                        menuTab.key -> stringResource(R.string.title_menu)
                        else -> throw IllegalArgumentException("Unknown key: $key")
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
        key: ScreenKey,
        radioTab: RadioTab,
        scheduleTab: ScheduleTab,
        menuTab: MenuTab,
        itemSelected: (Tab) -> Unit,
    ) {
        NavigationBar {
            BottomBarItem(
                selected = key == radioTab.key,
                textResource = R.string.title_radio,
                icon = Icons.Default.Radio,
                contentDescription = "Radio",
                itemSelected = { itemSelected(radioTab) },
            )
            BottomBarItem(
                selected = key == scheduleTab.key,
                textResource = R.string.title_schedule,
                icon = Icons.Default.Schedule,
                contentDescription = "Schedule",
                itemSelected = { itemSelected(scheduleTab) },
            )
            BottomBarItem(
                selected = key == menuTab.key,
                textResource = R.string.title_menu,
                icon = Icons.Default.Menu,
                contentDescription = "Menu",
                itemSelected = { itemSelected(menuTab) },
            )
        }
    }

    @Composable
    private fun RowScope.BottomBarItem(
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

    @Composable
    private fun Content(
        tabNavigator: TabNavigator,
        radioTab: RadioTab,
        paddingValues: PaddingValues,
        scheduleTab: ScheduleTab,
        menuTab: MenuTab
    ) {
        when (tabNavigator.current.key) {
            radioTab.key -> (tabNavigator.current as RadioTab).paddingValues =
                paddingValues

            scheduleTab.key -> (tabNavigator.current as ScheduleTab).paddingValues =
                paddingValues

            menuTab.key -> (tabNavigator.current as MenuTab).paddingValues =
                paddingValues

            else -> throw IllegalArgumentException("Unknown key: ${tabNavigator.current.key}")
        }
        CurrentTab()
    }
}