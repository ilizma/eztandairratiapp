package com.ilizma.main.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ilizma.main.view.model.BottomNavigation
import com.ilizma.menu.flow.model.MenuTab
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.player.flow.model.RadioTab
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.schedule.flow.model.ScheduleTab
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.component.ScheduleDetailScreen
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import com.ilizma.view.navigation.Navigator
import com.ilizma.view.navigation.rememberNavigationState
import com.ilizma.view.navigation.toEntries
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

private val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(BottomNavigation::class, BottomNavigation.serializer())
            subclass(ScheduleDetail::class, ScheduleDetail.serializer())
            subclass(RadioTab::class, RadioTab.serializer())
            subclass(ScheduleTab::class, ScheduleTab.serializer())
            subclass(MenuTab::class, MenuTab.serializer())
        }
    }
}

@Composable
fun AppNavigation(
    radioScreenRouter: RadioScreenRouter,
    radioScreenViewModel: RadioScreenViewModel,
) {
    EztandaIrratiappTheme(dynamicColor = false) {
        InitRadioScreen(
            radioScreenRouter = radioScreenRouter,
            radioScreenViewModel = radioScreenViewModel,
        )
    }
}

@Composable
private fun InitRadioScreen(
    radioScreenRouter: RadioScreenRouter,
    radioScreenViewModel: RadioScreenViewModel,
) {
    val scheduleScreenRouter: ScheduleScreenRouter = koinInject()
    val menuScreenRouter: MenuScreenRouter = koinInject()
    val scheduleDetailScreenRouter: ScheduleDetailRouter = koinInject()
    val scheduleScreenViewModel: ScheduleScreenViewModel = koinViewModel()
    val menuScreenViewModel: MenuScreenViewModel = koinViewModel()
    val scheduleDetailScreenViewModel: ScheduleDetailScreenViewModel = koinViewModel()

    val coroutineScope = rememberCoroutineScope()
    
    val navigationState = rememberNavigationState(
        configuration = navigationConfig,
        startRoute = BottomNavigation,
        topLevelRoutes = setOf(BottomNavigation),
    )
    val navigator = remember { Navigator(navigationState) }

    val bottomNavigationState = rememberNavigationState(
        configuration = navigationConfig,
        startRoute = RadioTab,
        topLevelRoutes = setOf(RadioTab, ScheduleTab, MenuTab)
    )
    val bottomNavigator = remember { Navigator(bottomNavigationState) }

    radioScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = radioScreenViewModel
    )
    scheduleScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = scheduleScreenViewModel,
        navController = navigator,
        bottomNavController = bottomNavigator
    )
    menuScreenRouter.init(
        uriHandler = LocalUriHandler.current,
        coroutineScope = coroutineScope,
        viewModel = menuScreenViewModel,
        navigator = bottomNavigator,
    )
    scheduleDetailScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = scheduleDetailScreenViewModel,
        navController = navigator,
    )

    Content(
        navigationState = navigationState,
        navigator = navigator,
        bottomNavigationState = bottomNavigationState,
        bottomNavigator = bottomNavigator,
        radioScreenViewModel = radioScreenViewModel,
        scheduleScreenViewModel = scheduleScreenViewModel,
        menuScreenViewModel = menuScreenViewModel,
        scheduleDetailScreenViewModel = scheduleDetailScreenViewModel,
    )
}

@Composable
private fun Content(
    navigationState: com.ilizma.view.navigation.NavigationState,
    navigator: Navigator,
    bottomNavigationState: com.ilizma.view.navigation.NavigationState,
    bottomNavigator: Navigator,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
    scheduleDetailScreenViewModel: ScheduleDetailScreenViewModel,
) {
    val entryProvider = entryProvider {
        entry<BottomNavigation> {
            BottomNavigation(
                navigationState = bottomNavigationState,
                navigator = bottomNavigator,
                radioScreenViewModel = radioScreenViewModel,
                scheduleScreenViewModel = scheduleScreenViewModel,
                menuScreenViewModel = menuScreenViewModel,
            )
        }

        entry<ScheduleDetail> { key ->
            ScheduleDetailScreen(
                viewModel = scheduleDetailScreenViewModel
                    .also { vm ->
                        vm.onIntent(
                            ScheduleDetailScreenIntent.SaveCache(
                                id = key.id,
                                name = key.name
                            )
                        )
                    }
                    .also { it.onIntent(ScheduleDetailScreenIntent.GetSchedule) },
            )
        }
    }

    NavDisplay(
        entries = navigationState.toEntries(entryProvider),
        onBack = { navigator.goBack() },
    )
}
