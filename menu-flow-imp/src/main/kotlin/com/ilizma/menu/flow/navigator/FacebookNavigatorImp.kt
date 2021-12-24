package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class FacebookNavigatorImp(
    private val context: Context,
) : FacebookNavigator {

    override fun navigate() {
        try {
            context.packageManager?.getPackageInfo("com.facebook.katana", 0)
            "fb://profile/100003479888864"
        } catch (e: Exception) {
            "https://www.facebook.com/eztanda.irratia"
        }.let { Uri.parse(it) }
            .let { context.startActivity(Intent(Intent.ACTION_VIEW, it)) }
    }

}