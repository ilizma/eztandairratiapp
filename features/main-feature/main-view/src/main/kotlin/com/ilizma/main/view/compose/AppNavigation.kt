package com.ilizma.main.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ilizma.main.view.model.AppItemType
import com.ilizma.main.view.model.BottomNavigation
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.schedule.presentation.viewmodel.ScheduleDetailScreenViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.compose.ScheduleDetailScreen
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope

@Composable
internal fun AppNavigation(
    scope: Scope,
) {
    val radioScreenRouter: RadioScreenRouter = koinInject(scope = scope)
    val scheduleScreenRouter: ScheduleScreenRouter = koinInject(scope = scope)
    val menuScreenRouter: MenuScreenRouter = koinInject(scope = scope)
    val scheduleDetailScreenRouter: ScheduleDetailRouter = koinInject(scope = scope)
    val radioScreenViewModel: RadioScreenViewModel = koinViewModel(scope = scope)
    val scheduleScreenViewModel: ScheduleScreenViewModel = koinViewModel()
    val menuScreenViewModel: MenuScreenViewModel = koinViewModel()
    val scheduleDetailScreenViewModel: ScheduleDetailScreenViewModel = koinViewModel()

    val navController = rememberNavController()
    val bottomNavController = rememberNavController()
    val navigationBarItemType = rememberSaveable { mutableStateOf(AppItemType.BOTTOM_NAVIGATION) }

    radioScreenRouter.init(
        viewModel = radioScreenViewModel,
    )
    scheduleScreenRouter.init(
        viewModel = scheduleScreenViewModel,
        navController = navController,
        bottomNavController = bottomNavController
    )
    menuScreenRouter.init(
        viewModel = menuScreenViewModel,
        navController = bottomNavController,
    )
    scheduleDetailScreenRouter.init(
        viewModel = scheduleDetailScreenViewModel,
        navController = navController,
    )

    Content(
        scope = scope,
        navigationBarItemType = { navigationBarItemType.value = it },
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
    scope: Scope,
    navigationBarItemType: (AppItemType) -> Unit,
    navController: NavHostController,
    bottomNavController: NavHostController,
    radioScreenViewModel: RadioScreenViewModel,
    scheduleScreenViewModel: ScheduleScreenViewModel,
    menuScreenViewModel: MenuScreenViewModel,
    scheduleDetailScreenViewModel: ScheduleDetailScreenViewModel
) {
    NavHost(navController = navController, startDestination = BottomNavigation) {
        composable<BottomNavigation> {
            navigationBarItemType(AppItemType.BOTTOM_NAVIGATION)
            BottomNavigation(
                scope = scope,
                navController = bottomNavController,
                radioScreenViewModel = radioScreenViewModel,
                scheduleScreenViewModel = scheduleScreenViewModel,
                menuScreenViewModel = menuScreenViewModel,
            )
        }

        composable<ScheduleDetail> { backStackEntry ->
            navigationBarItemType(AppItemType.SCHEDULE_DETAIL)
            ScheduleDetailScreen(
                viewModel = scheduleDetailScreenViewModel
                    .also { vm ->
                        backStackEntry.toRoute<ScheduleDetail>()
                            .let { vm.saveCache(it.id, it.name) }
                    }
                    .also { it.getSchedule() },
            )
        }
    }
}