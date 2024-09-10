package com.ilizma.main.view.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ilizma.main.view.model.AppItemType
import com.ilizma.main.view.model.BottomNavigation
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.schedule.flow.model.ScheduleDetail
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.view.compose.ScheduleDetailScreen
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.router.ScheduleScreenRouter

@Composable
internal fun AppNavigation(
    lazyViewModels: List<Lazy<ViewModel>>,
    radioScreenRouter: RadioScreenRouter,
    scheduleScreenRouter: ScheduleScreenRouter,
    scheduleDetailScreenRouter: ScheduleDetailRouter,
    menuScreenRouter: MenuScreenRouter,
) {
    val navController = rememberNavController()
    val bottomNavController = rememberNavController()
    val navigationBarItemType = rememberSaveable { mutableStateOf(AppItemType.BOTTOM_NAVIGATION) }

    radioScreenRouter.init()
    scheduleScreenRouter.init(
        navController = navController,
        bottomNavController = bottomNavController
    )
    menuScreenRouter.init(bottomNavController)
    scheduleDetailScreenRouter.init(navController)

    Content(
        lazyViewModels = lazyViewModels,
        navigationBarItemType = { navigationBarItemType.value = it },
        navController = navController,
        bottomNavController = bottomNavController,
    )
}

@Composable
private fun Content(
    lazyViewModels: List<Lazy<ViewModel>>,
    navigationBarItemType: (AppItemType) -> Unit,
    navController: NavHostController,
    bottomNavController: NavHostController,
) {
    NavHost(navController = navController, startDestination = BottomNavigation) {
        composable<BottomNavigation> {
            navigationBarItemType(AppItemType.BOTTOM_NAVIGATION)
            BottomNavigation(
                lazyViewModels = lazyViewModels,
                navController = bottomNavController,
            )
        }

        composable<ScheduleDetail> { backStackEntry ->
            navigationBarItemType(AppItemType.SCHEDULE_DETAIL)
            ScheduleDetailScreen(
                viewModel = (lazyViewModels.first { it.value is ScheduleScreenDetailViewModel }.value as ScheduleScreenDetailViewModel)
                    .also { vm ->
                        backStackEntry.toRoute<ScheduleDetail>()
                            .let { vm.saveCache(it.id, it.name) }
                    }
                    .also { it.getSchedule() },
            )
        }
    }
}