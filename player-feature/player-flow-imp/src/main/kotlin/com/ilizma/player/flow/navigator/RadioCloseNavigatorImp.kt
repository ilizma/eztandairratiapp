package com.ilizma.player.flow.navigator

import android.app.Activity

class RadioCloseNavigatorImp(
    private val activity: Activity,
) : RadioCloseNavigator {

    override fun close() {
        activity.finish()
    }

}