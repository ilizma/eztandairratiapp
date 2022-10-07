package com.ilizma.player.framework.media.di

import android.content.Context
import androidx.core.app.NotificationCompat
import com.ilizma.resources.R
import com.ilizma.player.framework.app.di.CHANNEL_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ServiceComponent::class)
object NotificationCompatBuilderModule {

    @Provides
    fun provideNotificationCompatBuilder(
        @ApplicationContext context: Context,
    ): NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .apply {
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setSmallIcon(R.drawable.ic_notification)
        }

}