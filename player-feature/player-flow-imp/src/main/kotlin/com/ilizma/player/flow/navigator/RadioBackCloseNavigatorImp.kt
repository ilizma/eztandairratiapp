package com.ilizma.player.flow.navigator

import android.content.Context
import android.content.Intent

class RadioBackCloseNavigatorImp(
    private val context: Context,
) : RadioBackCloseNavigator {

    override fun close() {
        Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_HOME)
            .let { context.startActivity(it) }
    }

}