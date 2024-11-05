package com.ilizma.review.framework

import android.app.Activity
import com.google.android.play.core.review.ReviewManager

class PlayReviewFrameworkImp(
    private val activity: Activity,
    private val manager: () -> ReviewManager,
) : PlayReviewFramework {

    override fun request() {
        manager().apply {
            requestReviewFlow().addOnSuccessListener {
                launchReviewFlow(activity, it)
            }
        }
    }

}