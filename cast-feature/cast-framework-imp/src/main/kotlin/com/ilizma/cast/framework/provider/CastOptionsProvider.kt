package com.ilizma.cast.framework.provider

import android.content.Context
import com.google.android.gms.cast.CastMediaControlIntent
import com.google.android.gms.cast.framework.CastOptions
import com.google.android.gms.cast.framework.OptionsProvider
import com.google.android.gms.cast.framework.SessionProvider
import com.google.android.gms.cast.framework.media.CastMediaOptions
import com.google.android.gms.cast.framework.media.NotificationOptions
import com.ilizma.cast.view.activity.ExpandedControlsActivity

class CastOptionsProvider : OptionsProvider {

    override fun getCastOptions(
        context: Context,
    ): CastOptions {
        val mediaOptions = NotificationOptions.Builder()
            .setTargetActivityClassName(ExpandedControlsActivity::class.java.name)
            .build()
            .let {
                CastMediaOptions.Builder()
                    .setNotificationOptions(it)
                    .setExpandedControllerActivityClassName(ExpandedControlsActivity::class.java.name)
                    .build()
            }
        return CastOptions.Builder()
            .setReceiverApplicationId(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID)
            .setCastMediaOptions(mediaOptions)
            .build()
    }

    override fun getAdditionalSessionProviders(
        context: Context,
    ): List<SessionProvider>? = null

}