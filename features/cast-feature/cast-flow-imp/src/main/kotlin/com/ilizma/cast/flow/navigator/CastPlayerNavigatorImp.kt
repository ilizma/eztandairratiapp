package com.ilizma.cast.flow.navigator

import android.app.Activity
import android.content.Intent
import com.ilizma.cast.view.activity.ExpandedControlsActivity

class CastPlayerNavigatorImp(
    private val activity: Activity,
) : CastPlayerNavigator {

    override fun navigate() {
        Intent(activity, ExpandedControlsActivity::class.java)
            .let { activity.startActivity(it) }
    }

}