package com.ilizma.menu.flow.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri

class WebNavigatorImp(
    private val context: Context,
) : WebNavigator {

    override fun navigate() {
        "http://eztanda.com/"
            .let { Uri.parse(it) }
            .let { context.startActivity(Intent(Intent.ACTION_VIEW, it)) }
    }

}