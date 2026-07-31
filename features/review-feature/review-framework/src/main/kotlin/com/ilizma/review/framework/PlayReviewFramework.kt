package com.ilizma.review.framework

import android.app.Activity
import com.google.android.play.core.review.ReviewManager

class PlayReviewFramework(
    private val activity: Activity,
    private val manager: () -> ReviewManager,
) {

    fun request() {
        manager().apply {
            requestReviewFlow().addOnSuccessListener {
                launchReviewFlow(activity, it)
            }
        }
    }

}