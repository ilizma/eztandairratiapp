package com.ilizma.main.view.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.app.ActivityCompat
import com.ilizma.main.view.compose.AppNavigation
import com.ilizma.review.framework.PlayReviewFramework
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()

    private val reviewFramework: PlayReviewFramework by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        // As a music player, the volume controls should adjust the music volume while in the app.
        volumeControlStream = AudioManager.STREAM_MUSIC

        reviewFramework.request()

        val requestPermissionLauncher = registerForActivityResult(RequestPermission()) {}

        setContent {
            checkPostNotificationPermission(
                activity = this,
                requestPermissionLauncher = requestPermissionLauncher,
            )
            KoinContext {
                AppNavigation(
                    radioScreenRouter = koinInject(scope = scope),
                    radioScreenViewModel = koinViewModel(scope = scope)
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
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