package com.ilizma.main.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ilizma.main.view.model.BottomNavigation
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.schedule.presentation.model.ScheduleDetailScreenIntent
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.component.ScheduleDetailScreen
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

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
    val navController = rememberNavController()
    val bottomNavController = rememberNavController()

    radioScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = radioScreenViewModel
    )
    scheduleScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = scheduleScreenViewModel,
        navController = navController,
        bottomNavController = bottomNavController
    )
    menuScreenRouter.init(
        uriHandler = LocalUriHandler.current,
        coroutineScope = coroutineScope,
        viewModel = menuScreenViewModel,
        navController = bottomNavController,
    )
    scheduleDetailScreenRouter.init(
        coroutineScope = coroutineScope,
        viewModel = scheduleDetailScreenViewModel,
        navController = navController,
    )

    Content(
        navController = navController,
        bottomNavController = bottomNavController,
        radioScreenViewModel = radioScreenViewModel,
        scheduleScreenViewModel = scheduleScreenViewModel,
        menuScreenViewModel = menuScreenViewModel,
        scheduleDetailScreenViewModel = scheduleDetailScreenViewModel,
    )
}

@Composable
private fun Content(
    navController: NavHostController,
    bottomNavController: NavHostController,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
    scheduleDetailScreenViewModel: ScheduleDetailScreenViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigation
    ) {
        composable<BottomNavigation> {
            BottomNavigation(
                navController = bottomNavController,
                radioScreenViewModel = radioScreenViewModel,
                scheduleScreenViewModel = scheduleScreenViewModel,
                menuScreenViewModel = menuScreenViewModel,
            )
        }

        composable<ScheduleDetail> { backStackEntry ->
            ScheduleDetailScreen(
                viewModel = scheduleDetailScreenViewModel
                    .also { vm ->
                        backStackEntry.toRoute<ScheduleDetail>()
                            .let {
                                vm.onIntent(
                                    ScheduleDetailScreenIntent.SaveCache(
                                        id = it.id,
                                        name = it.name
                                    )
                                )
                            }
                    }
                    .also { it.onIntent(ScheduleDetailScreenIntent.GetSchedule) },
            )
        }
    }
}