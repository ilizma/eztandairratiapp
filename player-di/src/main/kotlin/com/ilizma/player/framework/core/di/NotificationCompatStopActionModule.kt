package com.ilizma.player.framework.core.di

import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import com.ilizma.player.di.R
import com.ilizma.player.framework.app.di.STOP_PENDING_INTENT_NAMED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object NotificationCompatStopActionModule {

    @Provides
    fun provideNotificationManagerCompat(
        @Named(STOP_PENDING_INTENT_NAMED) stopPendingIntent: PendingIntent,
    ): NotificationCompat.Action = NotificationCompat.Action(
        R.drawable.ic_stop,
        getString(R.string.stop),
        stopPendingIntent
    )

}