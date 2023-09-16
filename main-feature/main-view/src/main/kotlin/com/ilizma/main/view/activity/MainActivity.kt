package com.ilizma.main.view.activity

import android.Manifest
import android.app.Activity
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
import com.ilizma.navigation.presentation.viewmodel.NavigationScreenViewModel
import com.ilizma.navigation.view.compose.NavigationScreen
import com.ilizma.player.presentation.viewmodel.RadioScreenViewModel
import com.ilizma.player.view.router.RadioScreenRouter
import com.ilizma.resources.ui.theme.EztandaIrratiappTheme
import com.ilizma.review.framework.PlayReviewFramework
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenDetailViewModel
import com.ilizma.schedule.presentation.viewmodel.ScheduleScreenViewModel
import com.ilizma.schedule.view.router.ScheduleDetailRouter
import com.ilizma.schedule.view.router.ScheduleScreenRouter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

private const val NAVIGATION_SCREEN_VIEW_MODEL_PROVIDER_NAMED =
    "NAVIGATION_SCREEN_VIEW_MODEL_PROVIDER_NAMED"
private const val RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED = "RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED"
private const val SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED =
    "SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED"
private const val MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED = "MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED"
private const val SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED =
    "SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var reviewFramework: PlayReviewFramework

    @Inject
    internal lateinit var radioScreenRouter: RadioScreenRouter

    @Inject
    internal lateinit var scheduleScreenRouter: ScheduleScreenRouter

    @Inject
    internal lateinit var menuScreenRouter: MenuScreenRouter

    @Inject
    internal lateinit var scheduleDetailScreenRouter: ScheduleDetailRouter

    @Named(NAVIGATION_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    @Inject
    internal lateinit var navigationViewModelProviderFactory: ViewModelProvider.Factory

    @Named(RADIO_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    @Inject
    internal lateinit var radioViewModelProviderFactory: ViewModelProvider.Factory

    @Named(SCHEDULE_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    @Inject
    internal lateinit var scheduleViewModelProviderFactory: ViewModelProvider.Factory

    @Named(MENU_SCREEN_VIEW_MODEL_PROVIDER_NAMED)
    @Inject
    internal lateinit var menuViewModelProviderFactory: ViewModelProvider.Factory

    @Named(SCHEDULE_DETAIL_VIEW_MODEL_PROVIDER_NAMED)
    @Inject
    internal lateinit var scheduleDetailViewModelProviderFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // As a music player, the volume controls should adjust the music volume while in the app.
        volumeControlStream = AudioManager.STREAM_MUSIC

        reviewFramework.request()

        val requestPermissionLauncher =
            registerForActivityResult(RequestPermission()) {}

        setContent {
            val navController = rememberNavController()
            val mainNavController = rememberNavController()
            scheduleDetailScreenRouter.init(navController)
            EztandaIrratiappTheme(dynamicColor = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    checkPostNotificationPermission(
                        activity = this,
                        requestPermissionLauncher = requestPermissionLauncher,
                    )
                    NavigationScreen(
                        lazyViewModels = listOf(
                            viewModels<NavigationScreenViewModel> { navigationViewModelProviderFactory },
                            viewModels<RadioScreenViewModel> { radioViewModelProviderFactory },
                            viewModels<ScheduleScreenViewModel> { scheduleViewModelProviderFactory },
                            viewModels<MenuScreenViewModel> { menuViewModelProviderFactory },
                            viewModels<ScheduleScreenDetailViewModel> { scheduleDetailViewModelProviderFactory },
                        ),
                        navController = navController,
                        mainNavController = mainNavController,
                        radioScreenRouter = radioScreenRouter,
                        scheduleScreenRouter = scheduleScreenRouter,
                        menuScreenRouter = menuScreenRouter,
                    )
                }
            }
        }
    }
}

fun checkPostNotificationPermission(
    activity: Activity,
    requestPermissionLauncher: ActivityResultLauncher<String>,
) {
    if (shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS).not()) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
