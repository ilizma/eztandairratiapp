package com.ilizma.main.view.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.ilizma.main.view.compose.AppNavigation
import com.ilizma.menu.presentation.viewmodel.MenuScreenViewModel
import com.ilizma.menu.view.router.MenuScreenRouter
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
            EztandaIrratiappTheme(dynamicColor = false) {
                checkPostNotificationPermission(
                    activity = this,
                    requestPermissionLauncher = requestPermissionLauncher,
                )
                AppNavigation(
                    lazyViewModels = listOf(
                        viewModels<RadioScreenViewModel> { radioViewModelProviderFactory },
                        viewModels<ScheduleScreenViewModel> { scheduleViewModelProviderFactory },
                        viewModels<MenuScreenViewModel> { menuViewModelProviderFactory },
                        viewModels<ScheduleScreenDetailViewModel> { scheduleDetailViewModelProviderFactory },
                    ),
                    radioScreenRouter = radioScreenRouter,
                    scheduleScreenRouter = scheduleScreenRouter,
                    scheduleDetailScreenRouter = scheduleDetailScreenRouter,
                    menuScreenRouter = menuScreenRouter,
                )
            }
        }
    }
}

private fun checkPostNotificationPermission(
    activity: Activity,
    requestPermissionLauncher: ActivityResultLauncher<String>,
) {
    // TODO create a dialog for the new versions if (shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS).not()) { }
    if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.POST_NOTIFICATIONS,
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
