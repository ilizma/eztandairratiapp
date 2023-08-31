package com.ilizma.navigation.view.compose

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.navigation.presentation.model.ScheduleDetailArgs
import com.ilizma.navigation.presentation.viewmodel.NavigationScreenViewModel
import com.ilizma.navigation.view.model.Routes.Main
import com.ilizma.navigation.view.model.Routes.ScheduleDetail
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.view.compose.ScheduleDetailScreen
import com.ilizma.schedule.view.router.ScheduleScreenRouter

@Preview
@Composable
fun NavigationScreen(
    @PreviewParameter(NavigationScreenPreviewProvider::class) lazyViewModels: List<Lazy<ViewModel>>,
    navController: NavHostController = rememberNavController(),
    radioScreenRouter: RadioScreenRouter,
    scheduleScreenRouter: ScheduleScreenRouter,
    menuScreenRouter: MenuScreenRouter,
) {
    NavHost(
        navController = navController,
        startDestination = Main.route,
    ) {
        composable(
            route = Main.route,
        ) {
            MainScreen(
                lazyViewModels = lazyViewModels,
                navController = navController,
                radioScreenRouter = radioScreenRouter,
                scheduleScreenRouter = scheduleScreenRouter,
                menuScreenRouter = menuScreenRouter,
            )
        }
        composable(
            route = ScheduleDetail.route,
            arguments = listOf(
                navArgument(
                    name = "id",
                ) { type = NavType.IntType },
                navArgument(
                    name = "name",
                ) { type = NavType.StringType },
            )
        ) { navBackStackEntry ->
            lazyViewModels.first { it.value is NavigationScreenViewModel }
                .value
                .let { it as NavigationScreenViewModel }
                .let { viewModel ->
                    ScheduleDetailArgs(
                        id = navBackStackEntry.arguments?.getInt("id")
                            ?: throw NullPointerException("argument id can not be null"),
                        name = navBackStackEntry.arguments?.getString("name")
                            ?: throw NullPointerException("argument name can not be null"),
                    ).let { viewModel.saveArgs(it) }
                }
            lazyViewModels.first { it.value is ScheduleScreenDetailViewModel }
                .value
                .let { it as ScheduleScreenDetailViewModel }
                .let {
                    BackHandler { it.onBack() }
                    ScheduleDetailScreen(viewModel = it)
                    it.getTitle()
                    it.getSchedule()
                }
        }
    }
}

private class NavigationScreenPreviewProvider : PreviewParameterProvider<List<Lazy<ViewModel>>> {
    override val values: Sequence<List<Lazy<ViewModel>>> = sequenceOf(
        listOf(lazy { FakeViewModel() }),
    )

    class FakeViewModel : ViewModel()
}
