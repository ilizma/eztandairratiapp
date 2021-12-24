package com.ilizma.player.framework.media.di

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.media.session.MediaButtonReceiver
import com.ilizma.player.di.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

private const val TAG = "MediaSessionCompatModule"

@Module
@InstallIn(FragmentComponent::class)
object MediaSessionCompatModule {

    @Provides
    fun provideMediaSessionCompat(
        context: Context,
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
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT,
        ).let { setSessionActivity(it) }

        val bitmap = AppCompatResources.getDrawable(context, R.drawable.img_eztanda)
            ?.toBitmap()
        MediaMetadataCompat.Builder()
            .let {
                it.putString(
                    MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE,
                    getString(R.string.radio_name)
                )
                it.putString(
                    MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE,
                    getString(R.string.free_radio)
                )
                it.putString(
                    MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION,
                    getString(R.string.listening)
                )
                it.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap)
                it.build()
            }.let { setMetadata(it) }
        bitmap?.recycle()
    }

}