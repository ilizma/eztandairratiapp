package com.ilizma.player.framework.media.di

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.media.session.MediaButtonReceiver
import com.ilizma.resources.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

private const val TAG = "MediaSessionCompatModule"

@Module
@InstallIn(ServiceComponent::class)
object MediaSessionCompatModule {

    @Provides
    fun provideMediaSessionCompat(
        mainActivityIntent: Intent,
        @ApplicationContext context: Context,
        resources: Resources,
    ): MediaSessionCompat = MediaSessionCompat(
        context,
        TAG,
        ComponentName(context, MediaButtonReceiver::class.java),
        null
    ).apply {
        setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)
        Intent(Intent.ACTION_MEDIA_BUTTON)
            .apply { setClass(context, MediaButtonReceiver::class.java) }
            .let { PendingIntent.getBroadcast(context, 0, it, 0) }
            .let { setMediaButtonReceiver(it) }
        PendingIntent.getActivity(
            context,
            0,
            mainActivityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT else PendingIntent.FLAG_UPDATE_CURRENT,
        ).let { setSessionActivity(it) }

        AppCompatResources.getDrawable(context, R.drawable.img_eztanda)
            ?.toBitmap()
            ?.let { bitmap ->
                MediaMetadataCompat.Builder()
                    .let {
                        it.putString(
                            MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE,
                            resources.getString(R.string.radio_name)
                        )
                        it.putString(
                            MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE,
                            resources.getString(R.string.free_radio)
                        )
                        it.putString(
                            MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION,
                            resources.getString(R.string.listening)
                        )

                        it.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap)
                        it.build()

                    }.let { setMetadata(it) }
                bitmap.recycle()
            }

    }

}