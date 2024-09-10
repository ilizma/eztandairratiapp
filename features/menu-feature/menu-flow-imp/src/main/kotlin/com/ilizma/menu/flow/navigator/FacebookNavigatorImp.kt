package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PackageInfoFlags
import android.net.Uri
import android.os.Build

class FacebookNavigatorImp(
    private val context: Context,
) : FacebookNavigator {

    override fun navigate() {
        try {
            val packageName = "com.facebook.katana"
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> context.packageManager.getPackageInfo(
                    packageName,
                    PackageInfoFlags.of(0)
                )

                else -> @Suppress("DEPRECATION")
                context.packageManager?.getPackageInfo(packageName, 0)
            }
            "fb://profile/100003479888864"
        } catch (e: Exception) {
            "https://www.facebook.com/eztanda.irratia"
        }.let { Uri.parse(it) }
            .let { context.startActivity(Intent(Intent.ACTION_VIEW, it)) }
    }

}