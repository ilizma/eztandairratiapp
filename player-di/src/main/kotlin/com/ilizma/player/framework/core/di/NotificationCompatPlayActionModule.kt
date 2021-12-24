package com.ilizma.player.framework.core.di

import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import com.ilizma.player.di.R
import com.ilizma.player.framework.app.di.START_PENDING_INTENT_NAMED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object NotificationCompatPlayActionModule {

    @Provides
    fun provideNotificationManagerCompat(
        @Named(START_PENDING_INTENT_NAMED) startPendingIntent: PendingIntent,
    ): NotificationCompat.Action = NotificationCompat.Action(
        R.drawable.ic_play,
        getString(R.string.play),
        startPendingIntent
    )

}