package com.ilizma.cast.flow.navigator

import android.content.Context
import android.content.Intent
import com.ilizma.cast.view.activity.ExpandedControlsActivity

class CastPlayerNavigator(
    private val context: Context,
) {

    fun navigate() {
        Intent(context, ExpandedControlsActivity::class.java)
            .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
            .let { context.startActivity(it) }
    }

}