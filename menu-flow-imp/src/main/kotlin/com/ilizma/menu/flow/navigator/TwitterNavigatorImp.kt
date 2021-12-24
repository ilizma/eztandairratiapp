package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class TwitterNavigatorImp(
    private val context: Context,
) : TwitterNavigator {

    override fun navigate() {
        try {
            context.packageManager?.getPackageInfo("com.twitter.android", 0)
            "twitter://user?user_id=1256809951"
        } catch (e: Exception) {
            "https://twitter.com/EztandaIrratia"
        }.let { Uri.parse(it) }
            .let { context.startActivity(Intent(Intent.ACTION_VIEW, it)) }
    }

}