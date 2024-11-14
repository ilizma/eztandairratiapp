package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PackageInfoFlags
import android.net.Uri
import android.os.Build

class TwitterNavigatorImp(
    private val context: Context,
) : TwitterNavigator {

    override fun navigate() {
        try {
            val packageName = "com.twitter.android"
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> context.packageManager.getPackageInfo(
                    packageName,
                    PackageInfoFlags.of(0)
                )

                else -> context.packageManager?.getPackageInfo(packageName, 0)
            }
            "twitter://user?user_id=1256809951"
        } catch (e: Exception) {
            "https://twitter.com/EztandaIrratia"
        }.let { Uri.parse(it) }
            .let { Intent(Intent.ACTION_VIEW, it) }
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

}