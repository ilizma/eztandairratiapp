package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PackageInfoFlags
import android.net.Uri
import android.os.Build

class InstagramNavigatorImp(
    private val context: Context,
) : InstagramNavigator {

    override fun navigate() {
        try {
            val packageName = "com.instagram.android"
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> context.packageManager.getPackageInfo(
                    packageName,
                    PackageInfoFlags.of(0)
                )

                else -> @Suppress("DEPRECATION")
                context.packageManager?.getPackageInfo(packageName, 0)
            }
            "http://instagram.com/_u/eztandairratia"
        } catch (e: Exception) {
            "http://instagram.com/eztandairratia"
        }.let { Uri.parse(it) }
            .let { context.startActivity(Intent(Intent.ACTION_VIEW, it)) }
    }

}