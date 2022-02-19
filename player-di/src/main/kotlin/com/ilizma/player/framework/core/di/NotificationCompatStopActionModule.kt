package com.ilizma.player.framework.core.di

import android.app.PendingIntent
import android.content.res.Resources
import androidx.core.app.NotificationCompat
import com.ilizma.player.di.R
import com.ilizma.player.framework.service.NOTIFICATION_COMPAT_STOP_ACTION_NAMED
import com.ilizma.player.framework.service.STOP_PENDING_INTENT_NAMED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import javax.inject.Named

@Module
@InstallIn(ServiceComponent::class)
object NotificationCompatStopActionModule {

    @Provides
    @Named(NOTIFICATION_COMPAT_STOP_ACTION_NAMED)
    fun provideNotificationManagerCompat(
        resources: Resources,
        @Named(STOP_PENDING_INTENT_NAMED) stopPendingIntent: PendingIntent,
    ): NotificationCompat.Action = NotificationCompat.Action(
        R.drawable.ic_stop,
        resources.getString(R.string.stop),
        stopPendingIntent
    )

}