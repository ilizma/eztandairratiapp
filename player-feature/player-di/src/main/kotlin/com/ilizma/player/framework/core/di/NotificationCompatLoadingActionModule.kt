package com.ilizma.player.framework.core.di

import android.app.PendingIntent
import android.content.res.Resources
import androidx.core.app.NotificationCompat
import com.ilizma.resources.R
import com.ilizma.player.framework.app.di.START_PENDING_INTENT_NAMED
import com.ilizma.player.framework.service.NOTIFICATION_COMPAT_LOADING_ACTION_NAMED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import javax.inject.Named

@Module
@InstallIn(ServiceComponent::class)
object NotificationCompatLoadingActionModule {

    @Provides
    @Named(NOTIFICATION_COMPAT_LOADING_ACTION_NAMED)
    fun provideNotificationManagerCompat(
        resources: Resources,
        @Named(START_PENDING_INTENT_NAMED) startPendingIntent: PendingIntent,
    ): NotificationCompat.Action = NotificationCompat.Action(
        null,
        "",
        startPendingIntent
    )

}